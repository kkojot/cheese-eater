package mouse.domain;

import board.domain.Board;
import board.domain.Point;
import io.vavr.collection.List;
import move.domain.ClosestCheeseStrategy;


public class CleverMouse extends Mouse {

    public CleverMouse(Board board) {
        this.board = board;
        this.path = getPath();
    }

    private CleverMouse(Board board, List<Point> path) {
        this.board = board;
        this.path = path;
    }

    protected Mouse createMouse(Board board, List<Point> path) {
        return new CleverMouse(board, path);
    }

    @Override
    public CleverMouse placeToStart() {
        final Board board = this.board.setPoint(newCurrentPoint(0, 0));
        return new CleverMouse(board, path);
    }

    @Override
    protected List<Point> getPath() {
        final ClosestCheeseStrategy moveStrategy = new ClosestCheeseStrategy(board);
        return moveStrategy.getPath();
    }
}
