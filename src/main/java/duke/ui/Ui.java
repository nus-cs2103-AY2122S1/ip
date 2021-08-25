package duke.ui;

import java.util.Scanner;

/**
 * Represents the user interface that handles the input and output of the program.
 *
 * @author ruiquan
 */
public class Ui {
    private final String LINE = "____________________________________________________________";
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Prints a loading error message.
     */
    public void showLoadingError() {
        String message = "Oops, looks like there is a problem loading duke.Duke";
        System.out.println(message);
    }

    /**
     * Prints a welcome message that includes a logo.
     */
    public void showWelcome() {
        String logo = """
                 ____        _       \s
                |  _ \\ _   _| | _____\s
                | | | | | | | |/ / _ \\
                | |_| | |_| |   <  __/
                |____/ \\__,_|_|\\_\\___|
                """;
        System.out.println(LINE);
        System.out.println(logo);
        System.out.println("Hi, I'm duke.Duke, your Personal Assistant Chatbot\n" +
                "What can I do for you today?");
        System.out.println(LINE);
    }

    /**
     * Prints a line which acts as a divider.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Prints an error message.
     * @param errorMessage the error message to be printed
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Reads and returns a String representing the user input.
     * @return a String representing the user input
     */
    public String readInput() {
        return scanner.nextLine();
    }

    /**
     * Prints a message.
     * @param message the message to be printed
     */
    public void reply(String message) {
        System.out.println(message);
    }
}
