package board.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void boardShouldBeInitializedCorrectly() {
        final Board board = Board.createBoard(3, 4);

        assertEquals(3, board.boardSize.width);
        assertEquals(4, board.boardSize.height);
        assertEquals(12, board.getBoard().length());
    }

    @Test
    void countBoardShouldReturnCorrectValue() {
        final Board board = Board.createBoard(4, 3);

        assertEquals(12, board.countItemOnBoard(PointType.EMPTY_PLACE));
    }

    @Test
    void setPointShouldBeCorrect() {
        final Board board = Board.createBoard(4, 3);
        final Point pointWithCheese = new Point(0, 0)
                .setType(PointType.CHEESE);
        final Point pointFromBoard = board
                .setPoint(pointWithCheese)
                .getBoard()
                .get(0);

        assertEquals(12, board.getBoard().size());
        assertEquals(PointType.CHEESE, pointFromBoard.type);
    }

}