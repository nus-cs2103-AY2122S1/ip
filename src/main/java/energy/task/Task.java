package energy.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * A class that contains the base functionality for a task.
 */
public class Task {
    protected final String taskName;
    protected boolean isDone;

    /**
     * Creates a task with a task name.
     *
     * @param taskName Short description of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Creates a task with a task name and whether or not the task is done.
     *
     * @param taskName Short description of the task.
     * @param isDone   Boolean that represents whether or not the task is done.
     */
    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    /**
     * Checks if the current task contains a given key phrase within its name.
     *
     * @param phrase A string that is to be check against the task name.
     * @return True if the task name contains this phrase, false otherwise.
     */
    public boolean containsPhrase(String phrase) {
        String capsInsensitiveTaskName = taskName.toLowerCase().trim();
        return capsInsensitiveTaskName.contains(phrase.toLowerCase().trim());
    }

    /**
     * Checks if the current task has the same date as the input date.
     * Proper functionality is present in the Deadline and Event classes.
     *
     * @param date A LocalDate object that contains date information.
     * @return False, since this class does not contain a date attribute.
     */
    public boolean hasSameDate(LocalDate date) {
        return false;
    }

    /**
     * Checks if the current task occurs before the input date and time.
     * Proper functionality is present in the Deadline and Event classes.
     *
     * @param dateTime A LocalDateTime object that contains date and time information.
     * @return False, since this class does not contain date/time attributes.
     */
    public boolean isBeforeDate(LocalDateTime dateTime) {
        return false;
    }

    /**
     * Marks the current task as done. Returns a new instance of the task to maintain immutability.
     *
     * @return A task with the same task name, but is marked as done.
     */
    public Task markAsDone() {
        return new Task(taskName, true);
    }

    /**
     * Converts the task data into its corresponding save file data format.
     *
     * @return A string to represent the data of the task in the save file.
     */
    public String toSaveData() {
        return isDone + "|" + taskName;
    }

    /**
     * Returns a string representation of the current task.
     *
     * @return A string that contains the information of the task.
     */
    @Override
    public String toString() {
        String statusString = isDone ? "X" : " ";
        return String.format("[%s] %s", statusString, taskName);
    }
}
