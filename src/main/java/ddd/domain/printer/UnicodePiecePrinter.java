package ddd.domain.printer;

import ddd.domain.models.ChessColor;
import ddd.domain.models.Piece;
import ddd.domain.models.pieces.*;

public class UnicodePiecePrinter implements PiecePrinter<String> {

    private static final String WHITE_PAWN = "\u2659";
    private static final String BLACK_PAWN = "\u265F";
    private static final String WHITE_KING = "\u2654";
    private static final String BLACK_KING = "\u265A";
    private static final String WHITE_KNIGHT = "\u2658";
    private static final String BLACK_KNIGHT = "\u265E";
    private static final String BLACK_ROOK = "\u265C";
    private static final String WHITE_ROOK = "\u2656";
    private static final String WHITE_BISHOP = "\u2657";
    private static final String BLACK_BISHOP = "\u265D";
    private static final String WHITE_QUEEN = "\u2655";
    private static final String BLACK_QUEEN = "\u265B";

    @Override
    public String print(Piece piece) {
        return switch (piece) {
            case Pawn pawn -> printPiece(pawn);
            case Rook rook -> printPiece(rook);
            case Bishop bishop -> printPiece(bishop);
            case King king -> printPiece(king);
            case Knight knight -> printPiece(knight);
            case Queen queen -> printPiece(queen);
            default -> throw new IllegalStateException("Unexpected value: " + piece);
        };
    }

    public String printPiece(Pawn piece) {
        return piece.getColor() == ChessColor.WHITE ? WHITE_PAWN : BLACK_PAWN;
    }

    public String printPiece(King piece) {
        return piece.getColor() == ChessColor.WHITE ? WHITE_KING : BLACK_KING;
    }

    public String printPiece(Knight piece) {
        return piece.getColor() == ChessColor.WHITE ? WHITE_KNIGHT : BLACK_KNIGHT;
    }

    public String printPiece(Rook piece) {
        return piece.getColor() == ChessColor.WHITE ? WHITE_ROOK : BLACK_ROOK;
    }

    public String printPiece(Bishop piece) {
        return piece.getColor() == ChessColor.WHITE ? WHITE_BISHOP : BLACK_BISHOP;
    }

    public String printPiece(Queen piece) {
        return piece.getColor() == ChessColor.WHITE ? WHITE_QUEEN : BLACK_QUEEN;
    }
}
