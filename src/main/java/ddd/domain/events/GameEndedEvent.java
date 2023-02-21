package ddd.domain.events;

import ddd.core.event.DomainEvent;
import ddd.domain.models.EndingReason;
import ddd.domain.models.GameId;
import ddd.domain.models.Move;
import ddd.domain.models.Player;

import java.util.List;
import java.util.Objects;

public class GameEndedEvent extends DomainEvent<GameId> {
    private final EndingReason reason;
    private final List<Move> movesMade;
    private final Player winner;

    public GameEndedEvent(GameId id, List<Move> movesMade, EndingReason reason, Player winner) {
        super(id);
        this.reason = reason;
        this.movesMade = movesMade;
        this.winner = winner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GameEndedEvent that = (GameEndedEvent) o;
        return reason == that.reason && movesMade.equals(that.movesMade) && winner.equals(that.winner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), reason, movesMade, winner);
    }

    @Override
    public String toString() {
        return "GameEndedEvent{" +
                "reason=" + reason +
                ", movesMade=" + movesMade +
                ", winner=" + winner +
                "} " + super.toString();
    }

    public EndingReason getReason() {
        return reason;
    }

    public List<Move> getMovesMade() {
        return movesMade;
    }

    public Player getWinner() {
        return winner;
    }
}
