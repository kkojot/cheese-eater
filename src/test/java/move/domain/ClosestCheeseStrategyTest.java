package move.domain;

import board.domain.Board;
import board.domain.Point;
import board.domain.PointType;
import io.vavr.collection.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClosestCheeseStrategyTest {

    public static final Board TEST_BOARD = initializeComplexBoardWithCheese();
    public static final Board TEST_BOARD_SIMPLE = initializeSimpleBoardWithCheese();

    private static Board initializeSimpleBoardWithCheese() {
        Board board = Board.createBoard(3, 3);
        board = board.setPoint(new Point(0, 2).setType(PointType.CHEESE));
        board = board.setPoint(new Point(1, 0).setType(PointType.CHEESE));
        board = board.setPoint(new Point(1, 2).setType(PointType.CHEESE));
        board = board.setPoint(new Point(2, 1).setType(PointType.CHEESE));
        return board;
    }

    private static Board initializeComplexBoardWithCheese() {
        Board board = Board.createBoard(10, 5);
        board = board.setPoint(new Point(0, 0).setType(PointType.CHEESE));
        board = board.setPoint(new Point(3, 0).setType(PointType.CHEESE));
        board = board.setPoint(new Point(1, 2).setType(PointType.CHEESE));
        board = board.setPoint(new Point(2, 3).setType(PointType.CHEESE));
        board = board.setPoint(new Point(3, 3).setType(PointType.CHEESE));
        board = board.setPoint(new Point(4, 6).setType(PointType.CHEESE));
        board = board.setPoint(new Point(0, 7).setType(PointType.CHEESE));
        board = board.setPoint(new Point(2, 7).setType(PointType.CHEESE));
        board = board.setPoint(new Point(3, 9).setType(PointType.CHEESE));

        return board;
    }

    @Test
    void closestCheeseMoveStrategyShouldBeCorrect() {
        System.out.println(TEST_BOARD);
        final ClosestCheeseStrategy moveStrategy = new ClosestCheeseStrategy(TEST_BOARD);
        final List<Point> path = moveStrategy.getPath();

        System.out.println(path);

        assertFalse(path.isEmpty());
        assertEquals(new Point(0, 0), path.get(0));
        assertEquals(new Point(0, 0), path.get(1));
        assertEquals(new Point(1, 0), path.get(2));
        assertEquals(new Point(2, 0), path.get(3));
        assertEquals(new Point(3, 0), path.get(4));
        assertEquals(new Point(3, 1), path.get(5));
        assertEquals(new Point(3, 2), path.get(6));
        assertEquals(new Point(3, 3), path.get(7));
    }

    @Test
    void pathBetweenShouldBeCorrect() {
        final ClosestCheeseStrategy moveStrategy = new ClosestCheeseStrategy(TEST_BOARD_SIMPLE);
        Point basePoint = new Point(0, 0);
        List<Point> path = moveStrategy.pathBetween(basePoint, new Point(1, 0));
        assertEquals(1, path.size());
        assertEquals(new Point(1, 0), path.get(0));

        path = moveStrategy.pathBetween(basePoint, new Point(0, 2));
        assertEquals(2, path.size());
        assertEquals(new Point(0, 1), path.get(0));
        assertEquals(new Point(0, 2), path.get(1));

        path = moveStrategy.pathBetween(basePoint, new Point(2, 1));
        assertEquals(3, path.size());
        assertEquals(new Point(1, 0), path.get(0));
        assertEquals(new Point(2, 0), path.get(1));
        assertEquals(new Point(2, 1), path.get(2));

        basePoint = new Point(2, 1);
        path = moveStrategy.pathBetween(basePoint, new Point(1, 0));
        assertEquals(2, path.size());
        assertEquals(new Point(1, 1), path.get(0));
        assertEquals(new Point(1, 0), path.get(1));

        path = moveStrategy.pathBetween(basePoint, new Point(0, 2));
        assertEquals(3, path.size());
        assertEquals(new Point(1, 1), path.get(0));
        assertEquals(new Point(0, 1), path.get(1));
        assertEquals(new Point(0, 2), path.get(2));

        basePoint = new Point(2, 7);
        path = moveStrategy.pathBetween(basePoint, new Point(0, 7));
        assertEquals(2, path.size());
        assertEquals(new Point(1, 7), path.get(0));
        assertEquals(new Point(0, 7), path.get(1));

    }

}