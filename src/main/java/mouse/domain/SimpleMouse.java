package mouse.domain;

import board.domain.Board;
import board.domain.Point;
import io.vavr.collection.List;
import move.domain.MoveStrategy;
import move.domain.SimpleMoveStrategy;


public class SimpleMouse extends Mouse {

    public SimpleMouse(Board board) {
        this.board = board;
        this.path = getPath();
    }

    private SimpleMouse(Board board, List<Point> path) {
        this.board = board;
        this.path = path;
    }

    protected Mouse createMouse(Board board, List<Point> path) {
        return new SimpleMouse(board, path);
    }

    @Override
    public Mouse placeToStart() {
        final Board board = this.board.setPoint(newCurrentPoint(0, 0));
        return new SimpleMouse(board, path);
    }

    @Override
    protected List<Point> getPath() {
        final MoveStrategy moveStrategy = new SimpleMoveStrategy(board);
        return moveStrategy.getPath();
    }

}
