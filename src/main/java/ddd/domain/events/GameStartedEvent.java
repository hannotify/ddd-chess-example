package ddd.domain.events;

import ddd.core.event.DomainEvent;
import ddd.domain.models.*;

import java.util.Objects;

public class GameStartedEvent extends DomainEvent<GameId> {
    private final Board board;
    private final GameState gameState;
    private final Player whitePlayer;
    private final Player blackPlayer;
    private final ChessColor firstMove;

    public GameStartedEvent(GameId id, Board board, GameState gameState, Player whitePlayer, Player blackPlayer, ChessColor firstMove) {
        super(id);
        this.board = board;
        this.gameState = gameState;
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.firstMove = firstMove;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GameStartedEvent that = (GameStartedEvent) o;
        return board.equals(that.board) && gameState == that.gameState && whitePlayer.equals(that.whitePlayer) && blackPlayer.equals(that.blackPlayer) && firstMove == that.firstMove;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), board, gameState, whitePlayer, blackPlayer, firstMove);
    }

    @Override
    public String toString() {
        return "GameStartedEvent{" +
                "board=" + board +
                ", gameState=" + gameState +
                ", whitePlayer=" + whitePlayer +
                ", blackPlayer=" + blackPlayer +
                ", firstMove=" + firstMove +
                "} " + super.toString();
    }

    public Board getBoard() {
        return board;
    }

    public GameState getGameState() {
        return gameState;
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }

    public ChessColor getFirstMove() {
        return firstMove;
    }
}
