package brobot;

import java.util.Scanner;

import brobot.task.Task;
import brobot.task.TaskList;

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
     * Print the inputted message out onto the terminal.
     * @param message The desired message to print
     */
    public static void printToTerm(String message) {
        System.out.println(message);
    }

    /**
     * Returns greeting message
     */
    public static String getGreetingText() {
        return greetingText;
    }

    /**
     * Returns Goodbye message
     */
    public static String getByeText() {
        return byeText;
    }

    /**
     * Returns 'list' command message
     * @param list The TaskList to be printed.
     */
    public static String getListText(TaskList list) {
        String s = "Alright here are the tasks in your list bro:" + list.toString();
        return formatWithSpace(s);
    }

    /**
     * Formats the amount of tasks in a list into a string.
     * @param count The amount of tasks in the list.
     * @return The formatted string.
     */
    public static String getTaskAmountText(int count) {
        return ("\nNow you have " + count + " task(s) in the list bro.");
    }

    /**
     * Returns message for newly added tasks
     * @param list The list with the new task added.
     */
    public static String getTaskAddedText(TaskList list) {
        int taskAmount = list.getTaskAmount();
        return formatWithSpace("Alright bro. I've added this task:\n"
                + list.getTask(taskAmount)
                + getTaskAmountText(taskAmount)
        );

    }

    /**
     * Returns the "task done" message for the specified task.
     * @param task The specified task.
     */
    public static String getTaskDoneText(Task task) {
        return formatWithSpace("Good job bro! I've marked this task as done:\n " + task);
    }

    /**
     * Returns the "task deleted" message for a specific task in a tasklist and the amount of tasks left.
     * @param task The deleted task.
     * @param list The list from which the task was deleted.
     */
    public static String getTaskDeletedText(Task task, TaskList list) {
        return formatWithSpace("Ok Bro, I removed the following task:\n "
                + task
                + getTaskAmountText(list.getTaskAmount()));
    }

    /**
     * Receives a user input on the terminal.
     * @return The input.
     */
    public static String getUserInput() {
        return scanner.nextLine();
    }

    /**
     * Returns the error message from the error.
     * @param e The error.
     */
    public static String getErrorText(Exception e) {
        return formatWithSpace(e.getMessage());
    }

    /**
     * Returns a list of the tasks in the provided list.
     * @param list The list to be printed.
     */
    public static String getSearchListText(TaskList list) {
        String s = "Here are the matching tasks in your list bro:" + list.toString();
        return formatWithSpace(s);
    }
}
