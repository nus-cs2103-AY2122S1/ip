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
     * Formats a string with a line spacing above and below for easier reading.
     * @param text The string to format.
     * @return The formatted string.
     */
    private static String formatWithSpace(String text) {
        return "\n" + text + "\n";
    }

    /**
     * Print the specified message onto the terminal
     * @param message The specified message to print
     */
    public static void printToTerm(String message) {
        System.out.println(message);
    }

    /**
     * Print the specified message onto the terminal with spacing on top and bottom
     * @param message The specified message to print
     */
    public static void printToTermFormatted(String message) {
        System.out.println(formatWithSpace(message));
    }

    /**
     * Returns greeting text
     */
    public static String getGreetingText() {
        return greetingText;
    }

    /**
     * Returns Goodbye text
     */
    public static String getByeText() {
        return byeText;
    }

    /**
     * Readable Text that shows the tasks in the specified task list
     * @param list The specified TaskList
     */
    public static String getListText(TaskList list) {
        String listText = "Alright here are the tasks in your list bro:" + list.toString();
        return listText;
    }

    /**
     * Readable Text that describes how many tasks are in a list.
     * @param count The amount of tasks in the list.
     * @return The formatted string.
     */
    public static String getTaskAmountText(int count) {
        return ("\nNow you have " + count + " task(s) in the list bro.");
    }

    /**
     * Readable Text to let users know when a task is added and the amount of tasks left.
     * @param list The list with the new task added.
     */
    public static String getTaskAddedText(TaskList list) {
        int lastTaskIndex = list.getTaskAmount();
        int taskAmount = list.getTaskAmount();
        String taskAddedText = "Alright bro. I've added this task:\n" + list.getTask(lastTaskIndex);
        return taskAddedText + getTaskAmountText(taskAmount);
    }

    /**
     * Readable text to let users know that a task has been marked as done.
     * @param task The specified task that has been marked done.
     */
    public static String getTaskDoneText(Task task) {
        return "Good job bro! I've marked this task as done:\n " + task;
    }

    /**
     * Readable text to let users know that the specified task has been deleted and afterwards, the amount
     * of tasks left in that task list.
     * @param task The deleted task.
     * @param list The list from which the task was deleted.
     */
    public static String getTaskDeletedText(Task task, TaskList list) {
        int taskAmount = list.getTaskAmount();
        String taskDeletedText = "Ok Bro, I removed the following task:\n " + task;
        return taskDeletedText + getTaskAmountText(taskAmount);
    }

    /**
     * Receives a user input on the terminal.
     * @return The input.
     */
    public static String getUserInput() {
        return scanner.nextLine();
    }

    /**
     * Readable text ot let the users know of the error message.
     * @param e The error.
     */
    public static String getErrorText(Exception e) {
        return e.getMessage();
    }

    /**
     * Readable text that shows the list of tasks matching the user's search keyword
     * @param list The list to be printed.
     */
    public static String getSearchListText(TaskList list) {
        String searchListText = "Here are the matching tasks in your list bro:" + list.toString();
        return searchListText;
    }

    /**
     * Readable text that lets the user know where the data file is newly located
     * @param newFileDirectory The new directory for the data file
     */
    public static String getStorageChangeText(String newFileDirectory) {
        String storageChangeText =
                "Alright bro, your list will now be saved in the following directory:\n" + newFileDirectory;
        return storageChangeText;
    }
}
