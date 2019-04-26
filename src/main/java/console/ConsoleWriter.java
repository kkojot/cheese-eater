package console;

import board.domain.Board;
import board.domain.CheesePutter;
import board.domain.PointType;
import io.vavr.control.Either;
import mouse.domain.CleverMouse;
import mouse.domain.Mouse;
import mouse.domain.SimpleMouse;


public class ConsoleWriter {

    public static void main(String[] args) {

        final Board board = Board.createBoard(10, 5);
        final Board boardWithCheese = CheesePutter.getBoardWithCheese(board, 10);
        final int cheeseCount = boardWithCheese.countItemOnBoard(PointType.CHEESE);

        Mouse simpleMouse =
                new SimpleMouse(boardWithCheese.copy())
                        .placeToStart();
        Mouse cleverMouse =
                new CleverMouse(boardWithCheese.copy())
                        .placeToStart();
        final String simpleMouseResult = mouseMove(simpleMouse, 300);
        final String cleverMouseResult = mouseMove(cleverMouse, 1000);

        System.out.println("ustawilismy " + cheeseCount + " kawalkow sera.");
        System.out.println(boardWithCheese);
        System.out.println();
        System.out.println(simpleMouseResult);
        System.out.println(simpleMouse);
        System.out.println();
        System.out.println(cleverMouseResult);
        System.out.println(cleverMouse);

        return;

    }

    private static String mouseMove(Mouse mouse, long sleepTime) {
        int moveCounter = 0;
        while (true) {
            clearConsole();
            final Either<Boolean, Mouse> move = mouse.move();
            if (move.isRight()) {
                mouse = move.get();
                System.out.println(mouse);
                moveCounter++;
            } else {
                return mouse.getClass().getSimpleName() + " zjadla ser w " + moveCounter + " ruchach.";
            }
            sleep(sleepTime);
        }
    }

    private static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void clearConsole() {
        for (int j = 0; j < 50; ++j) System.out.println();
    }
}
