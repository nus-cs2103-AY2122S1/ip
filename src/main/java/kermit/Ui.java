package kermit;

import java.util.Scanner;

import kermit.tasks.Task;

/**
 * Ui handles interface with user such as receiving user input and printing messages to user.
 */
public class Ui {
    private static final String introductionText =
            "Hello I am Kermit ( *・∀・)ノ゛, eaten any flies today?\nWhat can I do for you?";
    private static final String listText = "Here are the tasks in your list:";
    private static final String completeTaskText = "Ribbit Ribbit! Good job, task has been marked as complete:";
    private static final String filteredTaskText = "Here are the matching tasks in your list:";
    private static final String goodbyeText = "Bye. Hope to see you again soon!";
    private static final String errorText = "Burp burp! Something went wrong!";
    private static final String loadingErrorText = "Could not read this file Nuuuuuuu!";
    private Scanner sc;

    /**
     * Constructor for Ui.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }
    /**
     * Adds a top and bottom horizontal line to text.
     *
     * @param text Text to be formatted.
     * @return Formatted version of text.
     */
    private static String formatText(String text) {
        String horizontalDivider = "____________________________________________________________________________";
        return horizontalDivider + "\n" + text + "\n" + horizontalDivider;
    }

    private void formatAndPrintText(String text) {
        System.out.println(formatText(text));
    }

    /**
     * Pretty format and print welcome message.
     */
    public String getIntroMessage() {
        return introductionText;
    }

    /**
     * Pretty format and print exit message.
     */
    public String getGoodbyeMessage() {
        return goodbyeText;
    }

    /**
     * Pretty format and print list items.
     */
    public String getListItems(TaskList list) {
        return listText + "\n" + list.toString();
    }

    /**
     * Pretty format text when task is added.
     *
     * @param task Task that is added to list.
     * @param list List that task was added to.
     */
    public String getAddTaskMessage(Task task, TaskList list) {
        return "Got it. I've added this task:\n"
                + task + "\nNow you have " + list.size() + " tasks in the list.";
    }

    /**
     * Pretty format text when task is deleted.
     *
     * @param task Task that is deleted from list.
     * @param list List that task was deleted from.
     */
    public String getDeleteTaskMessage(Task task, TaskList list) {
        return "Noted. I've removed this task:\n"
                + task + "\nNow you have " + list.size() + " tasks in the list.";
    }

    /**
     * Pretty format text when task is completed.
     *
     * @param task Task that is completed.
     */
    public String getCompleteTaskMessage(Task task) {
        return completeTaskText + "\n" + task;
    }

    /**
     * Pretty format text error.
     *
     * @param e Error message.
     */
    public String getErrorMessage(String e) {
        return errorText + "\n" + e;
    }

    /**
     * Pretty format text error when loading file.
     */
    public String getLoadingError() {
        return loadingErrorText;
    }

    /**
     * Pretty format tasks that match filter.
     */
    public String getFilteredTasks(TaskList filteredTasks) {
        return filteredTaskText + "\n" + filteredTasks;
    }

    /**
     * Reads user input.
     *
     * @return user input.
     */
    public String readCommand() {
        return sc.nextLine();
    }
}
