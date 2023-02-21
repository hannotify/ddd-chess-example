package ddd.domain.models;

public record Square(File file, Rank rank) {

    public static Square of(File file, Rank rank) {
        return new Square(file, rank);
    }

    public ChessColor getColor() {
        if ((file.getOrdinal() + rank.getOrdinal()) % 2 == 0) {
            return ChessColor.BLACK;
        }

        return ChessColor.WHITE;
    }

    public Coordinate getCoordinates() {
        return new Coordinate(file.getOrdinal(), rank.getOrdinal());
    }

}
