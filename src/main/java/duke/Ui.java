package duke;

import java.util.List;
import java.util.Scanner;

import duke.task.Task;

/**
 * Represent a UI class that contains methods to print relevant
 * messages after processing user inputs.
 */
public class Ui {
    // 5 spaces, 50 dashes
    private static final String LINE = "     __________________________________________________\n";
    private static final String INDENT = "     "; // 5 spaces
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Print welcome message.
     */
    public static void printWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greeting = LINE
                + INDENT + "Hello! I'm Duke\n"
                + INDENT + "What can I do for you?\n"
                + LINE;
        System.out.println(greeting);
    }

    /**
     * Read user input.
     */
    public static String readInput() {
        return SCANNER.nextLine();
    }

    /**
     * Print all tasks currently in the list.
     *
     * @param listToPrint the task list to be printed.
     */
    public static void printList(List<Task> listToPrint) {
        System.out.println(LINE + INDENT + "Here are the tasks in your list:");
        for (int i = 0; i < listToPrint.size(); i++) {
            Task currTask = listToPrint.get(i);
            System.out.println(INDENT + (i + 1) + "." + currTask.toString());
        }
        System.out.println(LINE);
    }

    /**
     * Print error message.
     *
     * @param message error message.
     */
    public static void printError(String message) {
        System.out.println(LINE + INDENT + message + LINE);
    }

    /**
     * Print message for marking a task as done.
     *
     * @param taskString the task displayed as a string.
     */
    public static void printDoneTask(String taskString) {
        System.out.println(LINE + INDENT + "Nice! I've marked this task as done:\n"
                + INDENT + INDENT + taskString + "\n"
                + LINE);
    }

    /**
     * Print message for deleting a task.
     *
     * @param taskString the task displayed as a string.
     * @param taskNo number of the task.
     */
    public static void printDeleteTask(String taskString, int taskNo) {
        System.out.println(LINE + INDENT + "Noted. I've removed this task:\n"
                + INDENT + INDENT + taskString + "\n"
                + INDENT + String.format("Now you have %d tasks in the list.\n", taskNo)
                + LINE);
    }

    /**
     * Print message for finding a task, and display tasks found
     * based on keyword.
     *
     * @param matchingTaskList task list containing matching tasks.
     */
    public static void printFindTask(List<Task> matchingTaskList) {
        System.out.println(LINE + INDENT + "Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTaskList.size(); i++) {
            Task currTask = matchingTaskList.get(i);
            System.out.println(INDENT + (i + 1) + "." + currTask.toString());
        }
        System.out.println(LINE);
    }

    /**
     * Print message for adding a task.
     *
     * @param taskString the task displayed as a string.
     * @param taskNo number of the task.
     */
    public static void printAddTask(String taskString, int taskNo) {
        System.out.println(LINE + INDENT + "Got it. I've added this task:\n"
                + INDENT + INDENT + taskString + "\n"
                + INDENT + String.format("Now you have %d tasks in the list.\n", taskNo)
                + LINE);
    }

    /**
     * Print farewell message.
     */
    public static void printFarewell() {
        System.out.println(LINE + INDENT + "Bye. Hope to see you again soon!\n" + LINE);
    }
}
