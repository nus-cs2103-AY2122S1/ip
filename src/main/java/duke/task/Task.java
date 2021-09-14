package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Task is an abstract class that provides attributes and methods that are common to all types of task.
 */
public abstract class Task {
    protected static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task. Should not be used to instantiate Task object because Task is an abstract class.
     *
     * @param description Description of the task.
     * @param isDone Whether the task is done or not.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon of the task as a string.
     *
     * @return Status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the string representation of a task.
     *
     * @return A string representing a task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }


    /**
     * Converts the task info loaded from storage to its corresponding task object.
     *
     * @param taskInfo The information of the task loaded from storage.
     * @return The corresponding task.
     */
    public static Task convertToTask(String taskInfo) {
        String[] arr = taskInfo.split(" \\| ");
        char letter = arr[0].charAt(0);
        boolean isDone = arr[1].equals("X");
        switch (letter) {
        case 'T':
            return new Todo(arr[2], isDone);
        case 'D':
            LocalDate deadlineDate = LocalDate.parse(arr[3], Task.DATE_TIME_FORMATTER);
            LocalTime deadlineTime = LocalTime.parse(arr[4]);
            return new Deadline(arr[2], deadlineDate, deadlineTime, isDone);
        case 'E':
            LocalDate eventDate = LocalDate.parse(arr[3], Task.DATE_TIME_FORMATTER);
            String timeRange = arr[4];
            return new Event(arr[2], eventDate, timeRange, isDone);
        default:
            return null;
        }
    }

    /**
     * Converts the task to a string that can be saved to a file and converted back to itself.
     *
     * @return The string to be stored.
     */
    public abstract String stringToStore();
}
