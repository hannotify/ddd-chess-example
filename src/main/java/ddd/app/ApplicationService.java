package ddd.app;

import ddd.domain.GameAggregate;
import ddd.domain.command.MakeMoveCommand;
import ddd.domain.command.StartGameCommand;
import ddd.domain.exceptions.PolicyViolatedException;
import ddd.domain.models.*;

public class ApplicationService {
    private GameAggregate aggregate;

    public void doCommand(StartGameCommand command) {
        final GameId gameId = command.getId();
        aggregate = new GameAggregate(gameId);

        aggregate.start(gameId, new Player(command.getWhitePlayer(), ChessColor.WHITE), new Player(command.getBlackPlayer(), ChessColor.BLACK));
    }

    public void doCommand(MakeMoveCommand command) throws PolicyViolatedException {
        final GameId id = command.getId();

        final Move move = new Move(command.getPiece(), command.getStartPosition(), command.getEndPosition());
        aggregate.make(command.getPlayer(), move);
    }

    public void print(GameId id) {
        aggregate.printBoard();
    }

    void move(GameId gameId, Piece piece, Player player,
                             Square startPosition, Square endPosition, boolean isCapture) throws PolicyViolatedException {
        doCommand(new MakeMoveCommand(
                gameId,
                piece,
                startPosition,
                endPosition,
                false,
                player));
        print(gameId);
    }
}
