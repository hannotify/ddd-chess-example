package ddd.domain.exceptions;

import ddd.domain.models.Board;
import ddd.domain.models.Move;
import ddd.domain.printer.ConsoleBoardPrinter;
import ddd.domain.printer.FancyBoardPrinter;

public class IllegalChessMoveException extends Exception {
    private static final ConsoleBoardPrinter printer = new FancyBoardPrinter(System.out);
    private final Move move;
    private final Board board;

    public IllegalChessMoveException(String message, Move move, Board board) {
        super(message);
        this.move = move;
        this.board = board;
    }

    @Override
    public String getMessage() {
        final String before = printer.getRawString(board);
        final String after = printer.getRawString(board.commit(move));

        return """
                   %s with move %s
                   Before: %n%s
                   After: %n%s
               """.formatted(
                        super.getMessage(),
                        move.toString(),
                        before,
                        after);
    }
}
