package duke.util;

import java.util.ArrayList;

import duke.task.Task;

/**
 * This class encapsulates the UI used for displaying data to the user.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class Ui {
    public static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String DIVIDER =
            "\t" + "-------------------------------------------------------------------------";
    private static final String EMPTY_LIST_MESSAGE = "You are all done for the day :-)";

    /**
     * Formats message passed in and prints it out to the screen.
     *
     * @param message Message to be pretty printed.
     */
    public static void prettyPrint(String message) {
        System.out.printf(
                "%s\t%s%s",
                DIVIDER + LINE_SEPARATOR, message + LINE_SEPARATOR, DIVIDER + LINE_SEPARATOR);
    }

    /** Returns the exit message when user types in the exit command. */
    public static String getExitMessage() {
        return "Bye bye! See you again soon!";
    }

    /**
     * Prints all the task contained in the list.
     * Prints a customized message when list is empty.
     *
     * @param list the ArrayList containing all the tasks entered by the user.
     * @return Formatted string representation of what is in the list.
     */
    public static String printList(ArrayList<Task> list) {
        // Custom message for when user types 'list' when nothing is added.
        if (list.size() == 0) {
            return EMPTY_LIST_MESSAGE;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 1; i <= list.size(); i++) {
            sb.append(String.format("  %s. %s", i, list.get(i - 1) + LINE_SEPARATOR));
        }
        return sb.toString();
    }

    /**
     * Prints error/exceptions messages appropriately in System.err.
     *
     * @param message the error/exception message to be dispalyed.
     */
    public static void printException(String message) {
        System.err.println("\t" + message);
    }
}
