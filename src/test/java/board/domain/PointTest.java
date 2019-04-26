package board.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PointTest {

    @Test
    void newPointShouldHaveCorrectValues() {
        final Point point = new Point(2, 3);

        assertEquals(2, point.row);
        assertEquals(3, point.column);
        assertEquals(PointType.EMPTY_PLACE, point.type);
        assertEquals(PointStatus.NEW, point.status);
    }

    @Test
    void setNewTypeShouldBeCorrect() {
        final Point point = new Point(2, 3);
        final Point pointWithCheese = point.setType(PointType.CHEESE);

        assertEquals(PointType.CHEESE, pointWithCheese.type);
    }

    @Test
    void setNewStatusShouldBeCorrect() {
        final Point point = new Point(2, 3);
        final Point currentPoint = point.setStatus(PointStatus.CURRENT);

        assertEquals(PointStatus.CURRENT, currentPoint.status);
    }


    @Test
    void pointWithSamePositionShouldBeEquals() {
        final Point emptyPoint = new Point(2, 3);
        final Point cheesePoint = new Point(2, 3).setType(PointType.CHEESE);

        assertEquals(emptyPoint, cheesePoint);
    }

    @Test
    void distanceBetweenShouldBeCorrect() {
        Point basePoint = new Point(0, 0);

        assertEquals(2, basePoint.distanceBetween(new Point(0, 2)));
        assertEquals(1, basePoint.distanceBetween(new Point(1, 0)));
        assertEquals(3, basePoint.distanceBetween(new Point(1, 2)));

        basePoint = new Point(2, 1);

        assertEquals(2, basePoint.distanceBetween(new Point(1, 0)));
        assertEquals(3, basePoint.distanceBetween(new Point(0, 2)));
        assertEquals(3, basePoint.distanceBetween(new Point(0, 0)));
        assertEquals(0, basePoint.distanceBetween(new Point(2, 1)));
    }

}