package duke.ui;

import java.util.Scanner;

public class Ui {
    private static final Scanner in = new Scanner(System.in);

    public static String readCommand() {
        System.out.print(">> ");
        String command = in.nextLine();
        return command;
    }

    public static void greet() {
        System.out.println("Hello, how can I help you?");
    }

    public static void printError(String message) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(message);
    }
}
