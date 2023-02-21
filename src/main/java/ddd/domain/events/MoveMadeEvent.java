package ddd.domain.events;

import ddd.core.event.DomainEvent;
import ddd.domain.models.GameId;
import ddd.domain.models.Move;

import java.util.Objects;

public class MoveMadeEvent extends DomainEvent<GameId> {
    private final Move move;

    public MoveMadeEvent(GameId id, Move move) {
        super(id);
        this.move = move;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MoveMadeEvent that = (MoveMadeEvent) o;
        return move.equals(that.move);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), move);
    }

    @Override
    public String toString() {
        return "MoveMadeEvent{" +
                "move=" + move +
                "} " + super.toString();
    }

    public Move getMove() {
        return move;
    }
}
