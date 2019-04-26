package board.domain;

import java.util.Objects;

public class Point implements Comparable<Point> {

    public final int row;
    public final int column;
    public final PointType type;
    public final PointStatus status;

    public Point(int row, int column) {
        this.row = row;
        this.column = column;
        this.type = PointType.EMPTY_PLACE;
        this.status = PointStatus.NEW;
    }

    public Point setType(PointType type) {
        return new Point(row, column, type, status);
    }

    public Point setStatus(PointStatus status) {
        return new Point(row, column, type, status);
    }

    public int distanceBetween(Point endPoint) {
        int y = Math.abs(row - endPoint.row);
        int x = Math.abs(column - endPoint.column);
        return y + x;
    }

    private Point(int row, int column, PointType type, PointStatus status) {
        this.row = row;
        this.column = column;
        this.type = type;
        this.status = status;
    }

    private String printByType() {
        switch (type) {
            case EMPTY_PLACE:
                return "0";
            case CHEESE:
                return "S";
            default:
                return "?";
        }
    }

    @Override
    public int compareTo(Point point) {
        if (this.row != point.row) {
            return this.row - point.row;
        } else {
            return this.column - point.column;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return row == point.row &&
                column == point.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public String toString() {
        if (status == PointStatus.TRAVELED) return ".";
        if (status == PointStatus.CURRENT) return "M";
        return printByType();
    }

//    @Override
//    public String toString() {
//        return "[" + row + "," + column + "]";
//    }
}
