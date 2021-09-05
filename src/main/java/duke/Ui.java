package duke;

import java.util.Scanner;

/**
 * Utility to help deals with interactions with the user
 */
public class Ui {
    private final Scanner sc = new Scanner(System.in);

    /**
     * Greet the user.
     */
    private void sendGreetings() {
        String logo = "\t  ____        _        \n"
                + "\t |  _ \\ _   _| | _____ \n"
                + "\t | | | | | | | |/ / _ \\\n"
                + "\t | |_| | |_| |   <  __/\n"
                + "\t |____/ \\__,_|_|\\_\\___|\n";

        insertSeparateLine();
        System.out.println(logo);
        display("Hello! I'm Duke");
        display("What can I do for you?");
        insertSeparateLine();
    }

    /**
     * Send a "goodbye" message when the user closes the application.
     */
    private void sendFarewell() {
        Ui.displayWithLines("Bye. Hope to see you again soon!");
        sc.close();
    }

    /**
     * Print out the separation line between elements of the program
     */
    public static void insertSeparateLine() {
        String separateLine =
                "___________________________________________________________________________________";
        System.out.println("\t" + separateLine);
    }

    /**
     * Print out the formatted version of any string content
     *
     * @param content Content to display.
     */
    public static void display(String content) {
        System.out.println("\t" + " " + content);
    }

    /**
     * Print out the formatted version of any string content between two horizontal lines
     *
     * @param content Content to display.
     */
    public static void displayWithLines(String content) {
        insertSeparateLine();
        System.out.println("\t" + " " + content);
        insertSeparateLine();
    }

    /**
     * Start the user interface and begin the program
     */
    public void start() {
        sendGreetings();
        String currentCommand = sc.nextLine().trim();
        Parser parser = new Parser();
        while (!currentCommand.equals("bye")) {
            try {
                parser.invokeCommand(currentCommand);
            } catch (DukeInvalidCommandException e) {
                Ui.displayWithLines(e.getMessage());
            }
            currentCommand = sc.nextLine().trim();
        }
        sendFarewell();
    }
}
