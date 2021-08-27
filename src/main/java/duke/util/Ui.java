package duke.util;

import duke.task.Task;

import java.util.ArrayList;

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
    private static final String BANNER =
            "____    __    ____  _______  __        ______   ______   .___  ___.  _______    .___________.  ______       _______   __    __   __  ___  _______  __  \r\n"
                    + "\\   \\  /  \\  /   / |   ____||  |      /      | /  __  \\  |   \\/   | |   ____|   |           | /  __  \\     |       \\ |  |  |  | |  |/  / |   ____||  | \r\n"
                    + " \\   \\/    \\/   /  |  |__   |  |     |  ,----'|  |  |  | |  \\  /  | |  |__      `---|  |----`|  |  |  |    |  .--.  ||  |  |  | |  '  /  |  |__   |  | \r\n"
                    + "  \\            /   |   __|  |  |     |  |     |  |  |  | |  |\\/|  | |   __|         |  |     |  |  |  |    |  |  |  ||  |  |  | |    <   |   __|  |  | \r\n"
                    + "   \\    /\\    /    |  |____ |  `----.|  `----.|  `--'  | |  |  |  | |  |____        |  |     |  `--'  |    |  '--'  ||  `--'  | |  .  \\  |  |____ |__| \r\n"
                    + "    \\__/  \\__/     |_______||_______| \\______| \\______/  |__|  |__| |_______|       |__|      \\______/     |_______/  \\______/  |__|\\__\\ |_______|(__) \r\n"
                    + "                                                                                                                                                       ";

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

    /** Prints the welcome message when a user uses the bot for the first time. */
    public static void printWelcomeMessage() {
        System.out.println(BANNER);
        prettyPrint(
                "Hello! I'm Duke, your personal CLI bot!"
                        + LINE_SEPARATOR
                        + "\tI now function as a simple ToDoList."
                        + LINE_SEPARATOR
                        + "\tI can keep track of 3 different types of tasks:"
                        + LINE_SEPARATOR
                        + "\t\t- ToDo: To add a new todo task, type 'todo' followed by a task description."
                        + LINE_SEPARATOR
                        + "\t\t- Deadline: To add a new deadline, type 'deadline' followed by the task "
                        + "description and specify the deadline using '/by <dateTime>'"
                        + LINE_SEPARATOR
                        + "\t\t- Event: To add a new event, type 'event' followed by the event description "
                        + "and specify the timing using '/at <dateTime>'"
                        + LINE_SEPARATOR
                        + "\tTo see all your task currently, type 'list'."
                        + LINE_SEPARATOR
                        + "\tTo find task matching a certain keyword, type 'find <keyword>'."
                        + LINE_SEPARATOR
                        + "\tTo delete a task, type 'delete' followed by the index of the task you wish to "
                        + "delete (e.g delete 2)."
                        + LINE_SEPARATOR
                        + "\tYou can also mark tasks as done by typing 'done' followed by the index of the "
                        + "task you completed (e.g done 2)."
                        + LINE_SEPARATOR
                        + "\tOnce you are done, just type 'exit' to quit the program.");
    }

    /** Prints the exit message when user types in the exit command. */
    public static void printExitMessage() {
        prettyPrint("Bye bye! See you again soon!");
    }

    /**
     * Prints all the task contained in the list.
     * Prints a customized message when list is empty.
     *
     * @param list the ArrayList containing all the tasks entered by the user.
     */
    public static void printList(ArrayList<Task> list) {
        System.out.println(DIVIDER);
        // Custom message for when user types 'list' when nothing is added.
        if (list.size() == 0) {
            System.out.println("\tYou are all done for the day :-)");
        } else {
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 1; i <= list.size(); i++) {
                System.out.printf("\t%s. %s", i, list.get(i - 1) + LINE_SEPARATOR);
            }
        }
        System.out.println(DIVIDER);
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
