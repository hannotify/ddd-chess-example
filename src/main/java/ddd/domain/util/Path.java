package ddd.domain.util;

import ddd.domain.exceptions.IllegalBoardSquareException;
import ddd.domain.exceptions.UnexpectedChessException;
import ddd.domain.models.File;
import ddd.domain.models.Move;
import ddd.domain.models.Rank;
import ddd.domain.models.Square;
import ddd.domain.models.strategies.KnightJumpMovingStrategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Path {

    private Path() {
    }

    public static List<Square> constructFrom(Move move) {
        final List<Rank> ranks;
        final Square startSquare = move.endSquare();
        final Square endSquare = move.endSquare();
        if (startSquare.rank().getOrdinal() > endSquare.rank().getOrdinal()) {
            ranks = IntStream.rangeClosed(endSquare.rank().getOrdinal(), startSquare.rank().getOrdinal())
                    .boxed()
                    .sorted(Comparator.reverseOrder())
                    .map(Path::mapToRank)
                    .collect(Collectors.toList());
        } else {
            ranks = IntStream.rangeClosed(startSquare.rank().getOrdinal(), endSquare.rank().getOrdinal())
                    .boxed()
                    .map(Path::mapToRank)
                    .collect(Collectors.toList());
        }

        final List<File> files;
        if (startSquare.file().compareTo(endSquare.file()) > 0) {
            files = EnumSet.range(endSquare.file(), startSquare.file()).stream()
                    .sorted(Comparator.reverseOrder())
                    .collect(Collectors.toList());
        } else {
            files = new ArrayList<>(EnumSet.range(startSquare.file(), endSquare.file()));
        }

        final boolean knightJump = KnightJumpMovingStrategy.isKnightJump(ranks, files);
        if (knightJump) {
            return List.of(startSquare, endSquare);
        }

        return getPath(ranks, files);
    }

    private static List<Square> getPath(List<Rank> ranks, List<File> files) {
        final List<Rank> workRanks = new ArrayList<>(ranks);
        final List<File> workFiles = new ArrayList<>(files);

        if (workRanks.size() != workFiles.size()) {
            if (ranks.size() == 1) {
                for (int i = 0; i < (workFiles.size() - 1); i++) {
                    workRanks.add(workRanks.get(0));
                }
            }

            if (workFiles.size() == 1) {
                for (int i = 0; i < (workRanks.size() - 1); i++) {
                    workFiles.add(workFiles.get(0));
                }
            }
        }

        return IntStream.range(0, Math.min(workFiles.size(), workRanks.size()))
                .mapToObj(i -> new Square(workFiles.get(i), workRanks.get(i)))
                .collect(Collectors.toList());
    }

    private static Rank mapToRank(int intRank) {
        try {
            return new Rank(intRank);
        } catch (IllegalBoardSquareException e) {
            throw new UnexpectedChessException("Could not define range", e);
        }
    }
}
