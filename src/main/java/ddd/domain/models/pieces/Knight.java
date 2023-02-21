package ddd.domain.models.pieces;

import ddd.domain.models.ChessColor;
import ddd.domain.models.Piece;
import ddd.domain.models.strategies.KnightJumpMovingStrategy;
import ddd.domain.models.strategies.MovingStrategy;

import java.util.List;

public class Knight extends Piece {
    public Knight(ChessColor color) {
        super(color);
    }

    public static Piece black() {
        return new Knight(ChessColor.BLACK);
    }

    @Override
    public boolean canJumpOverPiece() {
        return true;
    }

    public static Piece white() {
        return new Knight(ChessColor.WHITE);
    }

    @Override
    protected List<MovingStrategy> getMovingStrategies() {
        return List.of(new KnightJumpMovingStrategy());
    }
}
