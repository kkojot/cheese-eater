package mouse.domain;

import board.domain.Board;
import board.domain.Point;
import board.domain.PointStatus;
import board.domain.PointType;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Option;

public abstract class Mouse {
    protected Board board;
    protected List<Point> path;

    protected abstract List<Point> getPath();

    public Board getBoard() {
        return board;
    }

    public abstract Mouse placeToStart();

    public Either<Boolean, Mouse> move() {
        if (!isThereStillCheese()) return Either.left(false);
        return getCurrentPoint()
                .flatMap(currentPoint -> path
                        .peekOption()
                        .map(newPoint -> move(currentPoint, newPoint)))
                .map(newBoard -> createMouse(newBoard, path.drop(1)))
                .toEither(false);
    }

    protected Option<Point> getCurrentPoint() {
        return board
                .getBoard()
                .find(point -> point.status == PointStatus.CURRENT);
    }

    protected Board move(Point currentPoint, Point newPoint) {
        return this.board
                .setPoint(traveledPoint(currentPoint))
                .setPoint(newCurrentPoint(newPoint));
    }

    protected abstract Mouse createMouse(Board board, List<Point> path);

    protected Point traveledPoint(Point point) {
        return new Point(point.row, point.column)
                .setStatus(PointStatus.TRAVELED);
    }

    protected Point newCurrentPoint(Point point) {
        return newCurrentPoint(point.row, point.column);
    }

    protected Point newCurrentPoint(int row, int column) {
        return new Point(row, column)
                .setStatus(PointStatus.CURRENT);
    }

    protected boolean isThereStillCheese() {
        return board
                .countItemOnBoard(PointType.CHEESE) > 0;
    }

    @Override
    public String toString() {
        return board.toString();
    }
}
