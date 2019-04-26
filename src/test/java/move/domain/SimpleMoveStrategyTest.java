package move.domain;

import board.domain.Board;
import board.domain.Point;
import board.domain.PointType;
import io.vavr.collection.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleMoveStrategyTest {

    public final Board testBoard = initializeDefaultBoardWithCheese();

    private static Board initializeDefaultBoardWithCheese() {
        Board board = Board.createBoard(3, 3);
        board = board.setPoint(new Point(0, 2).setType(PointType.CHEESE));
        board = board.setPoint(new Point(1, 0).setType(PointType.CHEESE));
        board = board.setPoint(new Point(1, 2).setType(PointType.CHEESE));
        board = board.setPoint(new Point(2, 1).setType(PointType.CHEESE));
        return board;
    }

    @Test
    void simpleMoveStrategyShouldBeCorrect() {
        System.out.println(testBoard);
        final MoveStrategy moveStrategy = new SimpleMoveStrategy(testBoard);
        final List<Point> path = moveStrategy.getPath();

        assertFalse(path.isEmpty());
        assertEquals(9, path.length());
        assertEquals(new Point(0, 0), path.get(0));
        assertEquals(new Point(0, 1), path.get(1));
        assertEquals(new Point(0, 2), path.get(2));
        assertEquals(new Point(1, 2), path.get(3));
        assertEquals(new Point(1, 1), path.get(4));
        assertEquals(new Point(1, 0), path.get(5));
        assertEquals(new Point(2, 0), path.get(6));
        assertEquals(new Point(2, 1), path.get(7));
        assertEquals(new Point(2, 2), path.get(8));

    }

}