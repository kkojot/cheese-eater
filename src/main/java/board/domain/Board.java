package board.domain;

import io.vavr.collection.List;

public class Board {

    public final BoardSize boardSize;
    private List<Point> board;

    private Board(BoardSize boardSize) {
        this.boardSize = boardSize;
        this.board = initializeBoardArray();
    }

    private Board(BoardSize boardSize, List<Point> board) {
        this.boardSize = boardSize;
        this.board = board;
    }

    public Board copy() {
        return new Board(boardSize, board);
    }


    public static Board createBoard(int width, int height) {
        BoardSize boardSize = new BoardSize(width, height);
        return new Board(boardSize);
    }

    public int countItemOnBoard(PointType type) {
        return board
                .filter(point -> point.type == type)
                .size();
    }

    public List<Point> getBoard() {
        return board;
    }

    public Board setPoint(Point point) {
        board = updatePoint(point)
                .sorted()
                .distinct();
        return this;
    }

    private synchronized List<Point> updatePoint(Point point) {
        return board
                .remove(point)
                .append(point);
    }

    private List<Point> initializeBoardArray() {
        final List<Point> points = List.range(0, boardSize.height)
                .flatMap(row -> List.range(0, boardSize.width)
                        .map(column -> new Point(row, column)));
        return points;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        int startX = 0;
        for (Point point : board) {
            if (point.row > startX) {
                stringBuilder.append("\n");
                startX++;
            }
            stringBuilder.append(point);
        }
        return stringBuilder.toString();
    }
}
