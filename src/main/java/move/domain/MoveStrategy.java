package move.domain;

import board.domain.Board;
import board.domain.Point;
import board.domain.PointType;
import io.vavr.collection.List;
import io.vavr.control.Option;

public abstract class MoveStrategy {
    public final Board board;

    public MoveStrategy(Board board) {
        this.board = board;
    }

    public abstract List<Point> getPath();

    protected Option<Point> oneStep(Point startPoint, MoveDirection direction) {
        return getNextPoint(startPoint, direction);
    }

    protected List<Point> manySteps(Point startPoint, MoveDirection direction) {
        return manySteps(startPoint, null, direction);
    }

    protected List<Point> manySteps(Point startPoint, Point endPoint, MoveDirection direction) {
        List<Point> path = List.empty();
        Option<Point> nextPoint = oneStep(startPoint, direction);
        while (nextPoint.isDefined()) {
            path = path.append(nextPoint.get());
            if (endPoint != null && nextPoint.get().equals(endPoint)) break;
            nextPoint = getNextPoint(nextPoint.get(), direction);
        }
        return path;
    }

    public List<Point> pathBetween(Point startPoint, Point endPoint) {
        if (startPoint.distanceBetween(endPoint) <= 1) return List.of(endPoint);
        List<Point> path = List.empty();
        if (startPoint.row - endPoint.row == 0) {
            path = moveHorizontally(startPoint, endPoint, path);
        } else {
            path = moveVertically(startPoint, new Point(endPoint.row, startPoint.column), path);
            startPoint = path.last();
            if (!startPoint.equals(endPoint))
                path = moveHorizontally(startPoint, endPoint, path);
        }
        return path.distinct();
    }

    private List<Point> moveVertically(Point startPoint, Point endPoint, List<Point> path) {
        if (startPoint.row - endPoint.row < 0) {
            path = path.appendAll(manySteps(startPoint, endPoint, MoveDirection.MOVE_DOWN));
        } else {
            path = path.appendAll(manySteps(startPoint, endPoint, MoveDirection.MOVE_UP));
        }
        return path;
    }

    private List<Point> moveHorizontally(Point startPoint, Point endPoint, List<Point> path) {
        if (startPoint.column - endPoint.column < 0) {
            path = path.appendAll(manySteps(startPoint, endPoint, MoveDirection.MOVE_RIGHT));
        } else {
            path = path.appendAll(manySteps(startPoint, endPoint, MoveDirection.MOVE_LEFT));
        }
        return path;
    }

    protected Point closestPoint(List<Point> points, Point startPoint) {
        return points
                .filter(point -> point.type == PointType.CHEESE)
                .toSortedMap(point -> point.distanceBetween(startPoint), point -> point)
                .get()
                ._2;
    }

    private Option<Point> getNextPoint(Point point, MoveDirection direction) {
        Option<Point> newPoint;
        switch (direction) {
            case MOVE_RIGHT:
                newPoint = getRightPoint(point);
                break;
            case MOVE_LEFT:
                newPoint = getLeftPoint(point);
                break;
            case MOVE_DOWN:
                newPoint = getDownPoint(point);
                break;
            case MOVE_UP:
                newPoint = getUpPoint(point);
                break;
            default:
                newPoint = Option.none();
        }
        return newPoint;
    }

    private Option<Point> getRightPoint(Point point) {
        if (point.column < board.boardSize.width - 1)
            return Option.of(new Point(point.row, point.column + 1));
        return Option.none();
    }

    private Option<Point> getDownPoint(Point point) {
        if (point.row < board.boardSize.height - 1)
            return Option.of(new Point(point.row + 1, point.column));
        return Option.none();
    }

    private Option<Point> getLeftPoint(Point point) {
        if (point.column > 0)
            return Option.of(new Point(point.row, point.column - 1));
        return Option.none();
    }

    private Option<Point> getUpPoint(Point point) {
        if (point.row > 0)
            return Option.of(new Point(point.row - 1, point.column));
        return Option.none();
    }
}
