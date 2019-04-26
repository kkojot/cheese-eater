package move.domain;

import board.domain.Board;
import board.domain.Point;
import board.domain.PointType;
import io.vavr.collection.List;

public class ClosestCheeseStrategy extends MoveStrategy {

    private final Point startPoint;

    public ClosestCheeseStrategy(Board board) {
        super(board);
        this.startPoint = new Point(0, 0);
    }

    @Override
    public List<Point> getPath() {
//        TODO wyglada jakby czasami wracal sie do pointa, w ktorym juz nie ma sera
        List<Point> path = List.of(startPoint);
        final int cheeseCount = board.countItemOnBoard(PointType.CHEESE);
        List<Point> points = this.board.getBoard();
        for (int i = 0; i < cheeseCount; i++) {
            final Point closestPoint = closestPoint(points, path.last());
            path = path.appendAll(pathBetween(path.last(), closestPoint));
            points = points.remove(closestPoint);

        }
        return path;
    }


}
