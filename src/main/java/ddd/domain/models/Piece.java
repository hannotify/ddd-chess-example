package ddd.domain.models;

import ddd.core.domain.ValueObject;
import ddd.domain.models.strategies.MovingStrategy;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public abstract class Piece implements ValueObject {
    private final ChessColor color;

    protected Piece(ChessColor color) {
        this.color = color;
    }

    public ChessColor getColor() {
        return color;
    }

    protected abstract List<MovingStrategy> getMovingStrategies();

    public List<Square> getRange(Square currentSquare) {
        return collectSquaresFromStrategies(strategy -> strategy.getRange(currentSquare));
    }

    public List<Square> getAttackRange(Square currentSquare) {
        return collectSquaresFromStrategies(strategy -> strategy.getAttackRange(currentSquare));
    }

    private List<Square> collectSquaresFromStrategies(Function<MovingStrategy, List<Square>> movingStrategyListFunction) {
        return getMovingStrategies().stream().map(movingStrategyListFunction)
                .flatMap(List::stream)
                .toList();
    }

    public boolean canJumpOverPiece() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
