package board.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheesePutterTest {

    @Test
    void minimalCheeseCountShouldBeCorrect() {
        Board board = Board.createBoard(20, 20);
        int cheeseCount = CheesePutter.minimalCheeseCount(board, 5);

        assertEquals(20, cheeseCount);

        board = Board.createBoard(10, 5);
        cheeseCount = CheesePutter.minimalCheeseCount(board, 10);

        assertEquals(5, cheeseCount);
    }

    @Test
    void roundMinimalCheeseCountShouldBeCorrect() {
        Board board = Board.createBoard(5, 5);
        int cheeseCount = CheesePutter.minimalCheeseCount(board, 9);

        assertEquals(3, cheeseCount);
    }

    @Test
    void generatedCheeseNumberShouldBeCorrect() {
        Board board = Board.createBoard(10, 10);
        int cheeseCount = CheesePutter.generateCheeseNumber(board, 10);

        assertTrue(cheeseCount >= 10 && cheeseCount <= 100);
    }

    @Test
    void expectedSizeShouldBeCorrect() {
        final Board board = Board.createBoard(23, 23);

        assertEquals(23*23, board.getBoard().size());
    }

    @Test
    void generatedBoardWithCheeseShouldBeCorrect() {
        final int minimalCheesePercentage = 10;
        final Board board = Board.createBoard(10, 4);

        final Board boardWithCheese = CheesePutter.getBoardWithCheese(board, minimalCheesePercentage);
        final int emptyPlacesCount = boardWithCheese.countItemOnBoard(PointType.EMPTY_PLACE);
        final int cheeseCount = boardWithCheese.countItemOnBoard(PointType.CHEESE);

//        System.out.println("empty:      " + emptyPlacesCount);
//        System.out.println("cheese:     " + cheeseCount);
//        System.out.println("min cheese: " + CheesePutter.minimalCheeseCount(board, minimalCheesePercentage));
//
//        System.out.println(boardWithCheese);

        assertTrue(cheeseCount >= CheesePutter.minimalCheeseCount(board, minimalCheesePercentage));
        assertEquals(board.getBoard().size(), emptyPlacesCount + cheeseCount);
    }



}