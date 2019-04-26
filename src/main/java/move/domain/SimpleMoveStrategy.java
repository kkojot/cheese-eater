package move.domain;

import board.domain.Board;
import board.domain.Point;
import io.vavr.collection.List;
import io.vavr.control.Option;

public class SimpleMoveStrategy extends MoveStrategy {

    private final Point startPoint;
    private List<MoveType> moveOrder = List.of(
            new MoveType(MoveDirection.MOVE_RIGHT, MoveTypes.MANY_STEPS),
            new MoveType(MoveDirection.MOVE_DOWN, MoveTypes.ONE_STEP),
            new MoveType(MoveDirection.MOVE_LEFT, MoveTypes.MANY_STEPS),
            new MoveType(MoveDirection.MOVE_DOWN, MoveTypes.ONE_STEP));

    public SimpleMoveStrategy(Board board) {
        super(board);
        this.startPoint = new Point(0, 0);
    }

    @Override
    public List<Point> getPath() {
        List<Point> path = List.of(startPoint);
        Option<Point> nextPoint;
        boolean lastStep = false;
//        TODO ladnie to jakos trzeba ogarnac
        while (!lastStep) {
            for (MoveType moveType : moveOrder) {
                if (moveType.type == MoveTypes.ONE_STEP) {
                    nextPoint = oneStep(path.last(), moveType.direction);
                    if (nextPoint.isDefined()) {
                        lastStep = false;
                        path = path.append(nextPoint.get());
                    } else {
                        lastStep = true;
                    }
                } else {
                    final List<Point> points = manySteps(path.last(), moveType.direction);
                    if (points.isEmpty()) {
                        lastStep = true;
                    } else {
                        path = path.appendAll(points);
                        lastStep = false;
                    }
                }
            }
        }
        path = path.distinct();

        return path;
    }


}
