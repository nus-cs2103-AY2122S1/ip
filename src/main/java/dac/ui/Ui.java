package dac.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import dac.task.Task;

/**
 * Represents the user interface of Duke. Deals with Duke's interaction with the user.
 */
public class Ui {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");

    public Ui() {}

    /**
     * Prints a farewell message.
     *
     * @return A farewell message.
     */
    public String farewell() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints a message saying the task list has been displayed.
     *
     * @return A message saying the task list has been displayed.
     */
    public String listMsg() {
        return "The tasks currently stored in the task list is shown on the right!";
    }

    /**
     * Prints a done message.
     * When user inputs a done instruction.
     *
     * @param task The task marked as done.
     * @return A message informing which task is done.
     */
    public String doneMsg(Task task) {
        return "Nice! I've marked this task as done:\n"
                + "\t" + task.toString();
    }

    /**
     * Prints a delete message.
     * When user inputs a delete instruction.
     *
     * @param task The task deleted.
     * @return A message informing which task is deleted.
     */
    public String deleteMsg(Task task) {
        return "Noted. I've removed this task: \n"
                + "\t" + task.toString() + "\n";
    }

    /**
     * Prints a add task message.
     * When user inputs a add task instruction.
     *
     * @param task The task added.
     * @return A message informing the task has been added.
     */
    public String addTaskMsg(Task task) {
        return "Got it. I've added this task:\n"
                + "\t" + task.toString() + "\n";
    }

    /**
     * Prints a message saying the tasks that match the given keyword has been displayed.
     *
     * @param keyword The keyword that the tasks match.
     * @return A message saying the tasks that match the given keyword has been displayed.
     */
    public String matchingKeyword(String keyword) {
        String text = "Tasks that match \"" + keyword + "\" are displayed on your right!";
        return text;
    }

    /**
     * Prints a message saying the tasks that occur on the given date has been displayed.
     *
     * @param date Matching tasks to be printed.
     * @return A message saying the tasks that match the given keyword has been displayed.
     */
    public String matchingDate(LocalDate date) {
        String text = "Tasks happening on "
                + date.format(DATE_FORMATTER)
                + " are displayed on your right!";
        return text;
    }

    /**
     * Prints a sort message.
     *
     * @param reverse Indicates if the tasks are sorted in reverse order.
     * @return A message informing the user that the tasks are sorted.
     */
    public String sortMessage(boolean reverse) {
        return reverse
                ? "Tasks are sorted by date in reverse order."
                : "Tasks are sorted by date.";
    }

    /**
     * Prints an exception message.
     *
     * @param e The Exception.
     * @param extra Extra details about the exception.
     * @return An exception message.
     */
    public String printException(Exception e, String extra) {
        String errorMessage = "ERROR ERROR ERROR ERROR ERROR\n"
                + e.toString();
        if (!extra.isEmpty()) {
            errorMessage += "\n";
        }
        return errorMessage + extra;
    }
}
