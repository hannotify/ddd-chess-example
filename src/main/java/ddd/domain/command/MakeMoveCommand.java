package ddd.domain.command;

import ddd.core.cqrs.Command;
import ddd.domain.models.GameId;
import ddd.domain.models.Piece;
import ddd.domain.models.Player;
import ddd.domain.models.Square;

public class MakeMoveCommand extends Command<GameId> {
    private final Piece piece;
    private final Square startPosition;
    private final Square endPosition;
    private final Boolean isCapture;
    private final Player player;

    public MakeMoveCommand(GameId id, Piece piece, Square startPosition, Square endPosition, Boolean isCapture, Player player) {
        super(id);
        this.piece = piece;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.isCapture = isCapture;
        this.player = player;
    }

    public Piece getPiece() {
        return piece;
    }

    public Square getStartPosition() {
        return startPosition;
    }

    public Square getEndPosition() {
        return endPosition;
    }

    public Boolean getCapture() {
        return isCapture;
    }

    public Player getPlayer() {
        return player;
    }
}
