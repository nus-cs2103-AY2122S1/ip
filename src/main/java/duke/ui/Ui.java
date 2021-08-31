package duke.ui;

import java.util.Scanner;

/**
 * Class containing methods forshowing text to the user.
 */
public class Ui {
    private static final Scanner in = new Scanner(System.in);

    /**
     * Reads a command from the user.
     *
     * @return Command from the user.
     */
    public static String readCommand() {
        System.out.print(">> ");
        String command = in.nextLine();
        return command;
    }

    /**
     * Greets the user.
     *
     * @return Greeting to the user.
     */
    public String greet() {
        System.out.println("Hello, how can I help you?");
        return "Hello, how can I help you?";
    }

    /**
     * Prints an error message.
     *
     * @param message Error message.
     */
    public static void printError(String message) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(message);
    }
}
