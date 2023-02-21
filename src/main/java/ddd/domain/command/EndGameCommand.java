package ddd.domain.command;

import ddd.core.cqrs.Command;
import ddd.domain.models.EndingReason;
import ddd.domain.models.GameId;

public class EndGameCommand extends Command<GameId> {
    private final EndingReason reason;
    public EndGameCommand(GameId id, EndingReason reason) {
        super(id);
        this.reason = reason;
    }

    public EndingReason getReason() {
        return reason;
    }
}
