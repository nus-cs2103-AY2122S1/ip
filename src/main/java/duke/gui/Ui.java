package duke.gui;

import java.util.Scanner;

/**
 * Ui is a class that provides useful methods for dealing with interactions with a user.
 */
public class Ui {
    private static Scanner sc = new Scanner(System.in);

    /**
     * Greets the user.
     */
    public static void greet() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        Ui.displayLine();
    }

    /**
     * Returns a string that bids farewell to the user.
     *
     * @return The farewell message.
     */
    public static String farewellMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Formats the output to be displayed in a pleasant manner and displays it to the user.
     *
     * @param output The output to be displayed to the user.
     */
    public static void formatAndPrint(String output) {
        displayLine();
        System.out.println(output);
        displayLine();
    }

    /**
     * Displays a horizontal line to the user.
     */
    public static void displayLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Reads input from the user.
     *
     * @return The input from the user in a String format.
     */
    public static String readInput() {
        return sc.nextLine();
    }

    /**
     * Displays a message to inform user that the storage file was not found.
     *
     * @param message Specific details of the error.
     */
    public static void showStorageFileNotFoundError(String message) {
        System.out.println("Storage file not found: " + message);
        System.out.println("Task List will be initialized to empty state.");
        System.out.println("Duke.txt will be created for you once you add tasks to the list.\n");
    }

}
