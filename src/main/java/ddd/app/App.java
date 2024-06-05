package ddd.app;

import ddd.domain.command.StartGameCommand;
import ddd.domain.exceptions.PolicyViolatedException;
import ddd.domain.models.*;
import ddd.domain.models.pieces.Pawn;

import static ddd.domain.models.File.*;
import static ddd.domain.models.Rank.*;

public class App {
    public static void main(String[] args) throws PolicyViolatedException {
        final var applicationService = new ApplicationService();
        final var gameId = new GameId("1");

        applicationService.doCommand(new StartGameCommand(gameId, "Hanno", "Gonard"));

        Player hanno = new Player("Hanno", ChessColor.WHITE);
        Player gonard = new Player("Gonard", ChessColor.BLACK);

        applicationService.move(gameId, Pawn.white(), hanno, Square.of(d, two), Square.of(d, four), false);
        applicationService.move(gameId, Pawn.black(), gonard, Square.of(e, seven), Square.of(e, five), false);
        applicationService.move(gameId, Pawn.white(), hanno, Square.of(d, four), Square.of(e, five), true);
    }
}
