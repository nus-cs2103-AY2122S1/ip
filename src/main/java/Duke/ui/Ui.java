package duke.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.exception.InvalidInputException;
import duke.exception.InvalidInstructionException;
import duke.exception.StorageMissingException;
import duke.task.Task;

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
     * Prints an invalid input message.
     * When the user enters an invalid input.
     *
     * @param e The InvalidInputException.
     * @param extra Extra details about the exception.
     * @return An invalid input message.
     */
    public String invalidInput(InvalidInputException e, String extra) {
        return e.toString() + " " + extra;
    }

    /**
     * Prints an invalid instruction message.
     * When the user enters an invalid instruction.
     *
     * @param e The InvalidInputException.
     * @param extra Extra details about the exception.
     * @return An invalid instruction message.
     */
    public String invalidInstruction(InvalidInstructionException e, String extra) {
        return e.toString() + " " + extra;
    }

    /**
     * Prints a storage missing message.
     * When the storage file is not found and cannot be created.
     *
     * @param e The StorageMissingException.
     * @param extra Extra details about the exception.
     * @return A storage missing message.
     */
    public String storageMissing(StorageMissingException e, String extra) {
        return e.toString() + " " + extra;
    }

    /**
     * Prints an invalid date time format message.
     * When the user inputs an invalid date time format.
     *
     * @param error Details about the exception.
     * @return An invalid date time format message.
     */
    public String invalidDateTimeFormat(String error) {
        return error;
    }
    /**
     * Prints an exception message.
     * When an exception that is not a Duke exception is thrown.
     *
     * @param e The non-Duke Exception.
     * @param extra Extra details about the exception.
     * @return An exception message.
     */
    public String printException(Exception e, String extra) {
        return e.toString() + " " + extra;
    }
}
