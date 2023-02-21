package ddd.domain.command;

import ddd.core.cqrs.Command;
import ddd.domain.models.GameId;

public class StartGameCommand extends Command<GameId> {
    private final String whitePlayer;
    private final String blackPlayer;

    public StartGameCommand(GameId id, String whitePlayer, String blackPlayer) {
        super(id);
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
    }

    public String getWhitePlayer() {
        return whitePlayer;
    }

    public String getBlackPlayer() {
        return blackPlayer;
    }
}
