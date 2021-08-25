package duke.util;

import duke.task.Task;

import java.util.Scanner;

/**
 * A medium between the user and Duke to communicate.
 * Scans for user inputs and format Duke's response before printing to screen.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Instantiates a ui instance to be used for receiving user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Format message and print to screen.
     * @param message message to be displayed.
     */
    public void respond(String message) {
        System.out.println("\n============Duke says============");
        System.out.println(message);
        System.out.println("=================================");
    }

    /**
     * Format response for find command.
     * @param tasks array of task to be printed.
     */
    public void findResponse(Task[] tasks) {
        System.out.println("\n============Duke says============");
        System.out.println("Found " + tasks.length + " tasks");
        for (Task task : tasks) {
            System.out.println(task.toString());
        }
        System.out.println("=================================");
    }

    /**
     * Retrieves next line of String.
     *
     * @return User's command sentence as a String.
     */
    public String nextCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints greetings.
     */
    public void greet() {
        System.out.println("Look at me! I'm DUKE\n"  + "How can I help?");
    }

    /**
     * Prints a prompt for user to send next command.
     */
    public void promptNext() {
        System.out.print("Whats next?");
    }
}
