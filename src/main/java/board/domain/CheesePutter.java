package board.domain;

import java.util.concurrent.ThreadLocalRandom;

public class CheesePutter {

    public static int minimalCheeseCount(Board board, int minimalCheesePercentage) {
        int boardSize = board.boardSize.height * board.boardSize.width;
        int cheeseCount = (int) Math.ceil(boardSize * (minimalCheesePercentage / 100.0));
        return cheeseCount;
    }

    public static int generateCheeseNumber(Board board, int minimalCheesePercentage) {
        final int minimum = minimalCheeseCount(board, minimalCheesePercentage);
        final int maximum = board.boardSize.height * board.boardSize.width;
        final int cheeseNumber = getRandomNumber(minimum, maximum);
        return cheeseNumber;
    }

    public static Board getBoardWithCheese(Board board, int minimalCheesePercentage) {
        final int cheeseNumber = generateCheeseNumber(board, minimalCheesePercentage);
        final int pointsWithoutCheese = board.getBoard().size() - cheeseNumber;
        board.getBoard()
                .toStream()
                .shuffle()
                .drop(pointsWithoutCheese)
                .map(point -> point.setType(PointType.CHEESE))
//                TODO think, is it ugly mutation of board passed as parameter?
                .forEach(point -> board.setPoint(point));
        return board;
    }

    private static int getRandomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

}
