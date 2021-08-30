package brobot;

import java.util.Scanner;

import brobot.task.TaskList;
import brobot.task.Task;

/**
 * Represents the User interface of the Duke Program. Interfaces through the terminal.
 */
public class UI {
    protected static String greetingText = "Hey Bro! I'm Brobot\nWhat can I do for you bro?\n";
    protected static String byeText = "Bye bro. Hope to see you again bro!";

    protected static Scanner scanner = new Scanner(System.in);

    /**
     * Formats a string with a line spacing above and below it and returns it.
     * @param text The string to format.
     * @return The formatted string.
     */
    private static String formatWithSpace(String text) {
        return "\n" + text + "\n";
    }

    /**
     * Prints the greeting message to the terminal.
     */
    public static void printGreeting() {
        System.out.println(greetingText);
    }

    /**
     * Prints the goodbye message to the terminal.
     */
    public static void printBye() {
        System.out.println(byeText);
    }

    /**
     * Prints a task list to the terminal.
     * @param list The TaskList to be printed.
     */
    public static void printList(TaskList list) {
        String s = "Alright here are the tasks in your list bro:" + list.toString();
        System.out.println(formatWithSpace(s));
    }

    /**
     * Formats the amount of tasks in a list into a string.
     * @param count The amount of tasks in the list.
     * @return The formatted string.
     */
    public static String listTaskAmount(int count) {
        return("\nNow you have " + count + " task(s) in the list bro.");
    }

    /**
     * Prints the "task added" message for a list.
     * @param list The list with the new task added.
     */
    public static void printTaskAdded(TaskList list) {
        int taskAmount = list.getTaskAmount();
        System.out.println(
                formatWithSpace("Alright bro. I've added this task:\n"
                        + list.getTask(taskAmount)
                        + listTaskAmount(taskAmount)
                )
        );

    }

    /**
     * Prints the "task done" message for the specified task.
     * @param task The specified task.
     */
    public static void printTaskDone(Task task) {
        System.out.println(formatWithSpace("Good job bro! I've marked this task as done:\n " + task));
    }

    /**
     * Prints the "task deleted" message for a specific task in a tasklist and the amount of tasks left.
     * @param task The deleted task.
     * @param list The list from which the task was deleted.
     */
    public static void printTaskDeleted(Task task,TaskList list) {
        System.out.println(
                formatWithSpace("Ok Bro, I removed the following task:\n "
                    + task
                    + listTaskAmount(list.getTaskAmount())));
    }

    /**
     * Receives a user input on the terminal.
     * @return The input.
     */
    public static String getUserInput() {
        return scanner.nextLine();
    }

    /**
     * Prints the error message to the terminal.
     * @param e The error.
     */
    public static void printError(Exception e) {
        System.out.println(formatWithSpace(e.getMessage()));
    }

    /**
     * Prints the list of matching tasks to the terminal.
     * @param list The list to be printed.
     */
    public static void printSearchList(TaskList list) {
        String s = "Here are the matching tasks in your list bro:" + list.toString();
        System.out.println(formatWithSpace(s));
    }
}
