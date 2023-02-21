package ddd.domain;

import ddd.core.domain.AggregateRoot;
import ddd.core.event.DomainEvent;
import ddd.domain.events.GameEndedEvent;
import ddd.domain.events.GameStartedEvent;
import ddd.domain.events.MoveMadeEvent;
import ddd.domain.exceptions.IllegalChessMoveException;
import ddd.domain.exceptions.PolicyViolatedException;
import ddd.domain.models.*;
import ddd.domain.models.rules.*;
import ddd.domain.printer.ConsoleBoardPrinter;
import ddd.domain.printer.FancyBoardPrinter;

import java.util.ArrayList;
import java.util.List;

import static ddd.domain.models.GameState.FINISHED;

public class GameAggregate extends AggregateRoot<GameId> {

    private Board board;
    private GameState gameState;

    public static final List<ChessMoveRule> rules = List.of(
            new TargetIsInAttackRangeRule(),
            new TargetIsInNormalRangeRule(),
            new PieceShouldBeOnStartSquareRule(),
            new PieceCanNotAttackOwnColorRule(),
            new PathIsObstructedRule(),
            new MoveShouldNotResultInCheckStateRule()
    );

    private final ConsoleBoardPrinter printer = new FancyBoardPrinter(System.out);

    private Player whitePlayer;
    private Player blackPlayer;
    private List<Move> movesMade;

    private ChessColor firstMove;

    public GameAggregate(GameId aggregateIdentifier) {
        super(aggregateIdentifier);
    }

    @Override
    protected void handle(DomainEvent<GameId> domainEvent) {
        switch (domainEvent) {
            case GameStartedEvent gse -> gameStarted(gse);
            case MoveMadeEvent mme -> moveMade(mme);
            case GameEndedEvent gee -> gameEnded(gee);
            case null, default -> throw new IllegalArgumentException("Unknown event type");
        }
    }

    private void gameStarted(GameStartedEvent event) {
        board = event.getBoard();
        gameState = event.getGameState();
        whitePlayer = event.getWhitePlayer();
        blackPlayer = event.getBlackPlayer();
        firstMove = event.getFirstMove();

        movesMade = new ArrayList<>();
    }

    private void moveMade(MoveMadeEvent event) {
        movesMade.add(event.getMove());
    }

    private void gameEnded(GameEndedEvent gee) {
        gameState = FINISHED;
    }

    public void start(GameId gameId, Player player1, Player player2) {
        final GameStartedEvent gameStartedEvent = new GameStartedEvent(gameId, BoardCreator.fullBoard(), GameState.STARTED, player1, player2, ChessColor.WHITE);
        raiseEvent(gameStartedEvent);
    }

    public void make(Player player, Move move) throws PolicyViolatedException {
        if (gameState != GameState.STARTED) {
            throw new PolicyViolatedException("Cannot make move when game is not started");
        }

        if (!inTurn(player.color())) {
            throw new PolicyViolatedException("It's not your turn");
        }

        if (player.color() != move.piece().getColor()) {
            throw new PolicyViolatedException("You cannot move the pieces of your opponent");
        }

        try {
            board = Board.make(move, board);
        } catch (IllegalChessMoveException e) {
            throw new PolicyViolatedException("Unable to make move", e);
        }

        raiseEvent(new MoveMadeEvent(getId(), move));

        if (CheckMateRule.isCheckMate(board, ChessColor.WHITE)) {
            raiseEvent(new GameEndedEvent(getId(), movesMade, EndingReason.BLACK_WON, blackPlayer));
        }

        if (CheckMateRule.isCheckMate(board, ChessColor.BLACK)) {
            raiseEvent(new GameEndedEvent(getId(), movesMade, EndingReason.WHITE_WON, whitePlayer));
        }
    }

    private boolean inTurn(ChessColor color) {
        return !((movesMade.size() + 1) % 2 == 1 && color == firstMove.invert());
    }

    public void printBoard() {
        printer.print(board);
    }
}
