package ddd.domain.models.pieces;

import ddd.domain.models.ChessColor;
import ddd.domain.models.Piece;
import ddd.domain.models.strategies.HorizontalMovingStrategy;
import ddd.domain.models.strategies.MovingStrategy;
import ddd.domain.models.strategies.VerticalMovingStrategy;

import java.util.List;

public class Rook extends Piece {
    public Rook(ChessColor color) {
        super(color);
    }

    public static Piece black() {
        return new Rook(ChessColor.BLACK);
    }

    public static Piece white() {
        return new Rook(ChessColor.WHITE);
    }

    @Override
    protected List<MovingStrategy> getMovingStrategies() {
        return List.of(new HorizontalMovingStrategy(MovingStrategy.RangeMode.UNLIMITED),
                new VerticalMovingStrategy(MovingStrategy.RangeMode.UNLIMITED));
    }
}
