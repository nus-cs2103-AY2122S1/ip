package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The Task Class is a representation of a task that Duke is keeping track of.
 *
 * It contains information relating to the task:
 * - description
 * - isDone
 *
 * @author Benedict Chua
 */
public abstract class Task implements Comparable<Task> {
    private static final String INDENTATION = "  ";
    private String description;
    private boolean isDone;

    /**
     * Constructor for a Task.
     *
     * @param description The name of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Convert the Task into a suitable String for saving into file.
     * To be overridden by child classes to call formatString in a suitable way.
     *
     * @return String of the formatted Task.
     */
    public abstract String convertToString();

    /**
     * Gets the date of the task.
     * Default null if type of task doesn't support date.
     *
     * @return LocalDate of task.
     */
    public LocalDate getDate() {
        return null;
    }

    /**
     * Gets the time of the task.
     * Default null if type of task doesn't support time.
     *
     * @return LocalTime of task.
     */
    public LocalTime getTime() {
        return null;
    }

    /**
     * Checks if the task is due before the given date.
     * Defaults to false for tasks without dates.
     *
     * @param date LocalDate to be checked against
     * @return boolean of the result from the check.
     */
    public boolean checkDueBeforeDate(LocalDate date) {
        return false;
    }

    /**
     * Returns the status of whether the task has been completed.
     *
     * @return "✓" if is completed, "✗" if not completed
     */
    public String getStatusIcon() {
        String doneIcon = "✓";
        String incompleteIcon = "✗";
        return (this.isDone ? doneIcon : incompleteIcon);
    }

    /**
     * Checks if task is done and marks task as done if task is not done
     *
     * @return String containing the description of task if not already done, else message that action is redundant.
     */
    public String markTaskAsDone() {
        if (!this.isDone) {
            this.isDone = true;
            return Task.INDENTATION + this.toString();
        } else {
            return String.format("...Wait.  You've already completed this task: '%s' before you dummy!",
                this.description
            );
        }
    }

    /**
     * Formats the task into a suitable String for saving.
     *
     * @return String containing formatted Task.
     */
    public String formatString(String ...args) {
        switch (args.length) {
        case 1:
            // returns type of task, completion and description as a string
            return String.format("%s | %d | %s", args[0], this.isDone ? 1 : 0, this.description);
        case 2:
            // returns additional date element
            return String.format("%s | %d | %s | %s", args[0], this.isDone ? 1 : 0, this.description, args[1]);
        default:
            throw new AssertionError("Too many arguments for formatting String of Task");
        }

    }

    /**
     * Checks if task is completed and returns the result.
     *
     * @return boolean of whether task is completed or not.
     */
    public boolean checkIsCompleted() {
        return this.isDone;
    }

    /**
     * Checks if task is due on the given date and returns the result.
     *
     * @param date String containing the date in the form dd/mm/yyyy, dd-mm-yyyy or yyyy-mm-dd.
     * @return boolean of the result.
     */
    public boolean isOnDate(String date) {
        return false;
    }

    /**
     * Checks if tasks description contains the keyword given and returns the result.
     * Both description and keyword will be case insensitive for check.
     *
     * @param keyword String of the keyword to be checked against the description.
     * @return boolean of whether description contains the keyword.
     */
    public boolean containsKeyword(String keyword) {
        int result = this.description.toLowerCase().indexOf(keyword.toLowerCase());
        return result != -1;
    }

    /**
     * Compares this task to the other task and returns a value based on which is more urgent.
     * Urgency of a task depends on:
     *   1. isDone
     *   2. dueDate (tasks with due dates are more urgent than tasks without)
     *   3. order of task in the TaskList
     *
     * @param other the other Task to compare to
     * @return
     */
    @Override
    public int compareTo(Task other) {
        if (this.isDone && !other.isDone) {
            return 1;
        } else if (this.isDone && other.isDone) {
            return -1;
        } else if (!this.isDone && other.isDone) {
            return -1;
        }

        if (this instanceof ToDo && other instanceof ToDo) {
            return -1;
        } else if (this instanceof ToDo && !(other instanceof ToDo)) {
            return 1;
        } else {
            assert !(this instanceof ToDo) : "This class is a ToDo";

            int compareDate = this.getDate().compareTo(other.getDate());

            if (compareDate == 0) {
                return this.getTime().compareTo(other.getTime());
            }

            return compareDate;
        }
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }
}
