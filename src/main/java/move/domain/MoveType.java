package move.domain;

public class MoveType {

    public final MoveDirection direction;
    public final MoveTypes type;

    public MoveType(MoveDirection direction, MoveTypes type) {
        this.direction = direction;
        this.type = type;
    }
}
