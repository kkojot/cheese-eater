package mouse.domain;

import board.domain.Board;
import board.domain.Point;
import board.domain.PointStatus;
import board.domain.PointType;
import io.vavr.collection.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleMouseTest {

    public final Board testBoard = initializeDefaultBoardWithCheese();

    private static Board initializeDefaultBoardWithCheese() {
        Board board = Board.createBoard(10, 5);
        board = board.setPoint(new Point(0, 3).setType(PointType.CHEESE));
        board = board.setPoint(new Point(0, 5).setType(PointType.CHEESE));
        board = board.setPoint(new Point(0, 6).setType(PointType.CHEESE));
        board = board.setPoint(new Point(0, 7).setType(PointType.CHEESE));
        board = board.setPoint(new Point(1, 0).setType(PointType.CHEESE));
        board = board.setPoint(new Point(1, 8).setType(PointType.CHEESE));
        board = board.setPoint(new Point(2, 1).setType(PointType.CHEESE));
        board = board.setPoint(new Point(2, 5).setType(PointType.CHEESE));
        board = board.setPoint(new Point(2, 9).setType(PointType.CHEESE));
        board = board.setPoint(new Point(3, 2).setType(PointType.CHEESE));
        board = board.setPoint(new Point(3, 4).setType(PointType.CHEESE));
        board = board.setPoint(new Point(4, 1).setType(PointType.CHEESE));
        board = board.setPoint(new Point(4, 8).setType(PointType.CHEESE));
        return board;
    }

    @Test
    void mouseShouldBeAtFirstPosition() {
        final Mouse simpleMouse = new SimpleMouse(testBoard).placeToStart();
        final Point startPoint = simpleMouse.getBoard().getBoard().get(0);

        assertEquals(PointStatus.CURRENT, startPoint.status);
    }

    @Test
    void moveRightShouldBeCorrect() {
        final Mouse simpleMouse =
                new SimpleMouse(testBoard)
                        .placeToStart()
                        .move()
                        .get()
                        .move()
                        .get();
        final List<Point> board = simpleMouse.getBoard().getBoard();

//        System.out.println(simpleMouse);

        assertEquals(PointStatus.TRAVELED, board.get(0).status);
        assertEquals(PointStatus.CURRENT, board.get(1).status);
    }

}