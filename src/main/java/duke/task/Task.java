package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The task object.
 */
public class Task {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

    private String description;
    private boolean isDone;
    private boolean hasReminder;
    private LocalDateTime reminderTime;

    /**
     * Constructs a default Task.
     */
    public Task() {
        this.description = "";
        this.isDone = false;
        hasReminder = false;
    }

    /**
     * Constructs Task.
     *
     * @param description Description for task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        hasReminder = false;
    }

    /**
     * Constructs Task with reminder.
     *
     * @param description  Description for task.
     * @param reminderTime The time when a reminder message display.
     */
    public Task(String description, String reminderTime) {
        this.description = description;
        this.hasReminder = true;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        this.reminderTime = LocalDateTime.parse(reminderTime, formatter);
    }

    /**
     * Marks this task as done.
     */
    public void done() {
        this.isDone = true;
    }

    /**
     * Returns the task status icon.
     *
     * @return Icon indicating whether this task is done or not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns task status.
     *
     * @return Integer with 1 indicating task has been done and 0 has not been done.
     */
    public int getStatus() {
        return isDone ? 1 : 0;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Shows this task's description.
     *
     * @return The task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Shows the time that the task will happen.
     *
     * @return The task's deadline or data.
     */
    public String getTaskTime() {
        return "";
    }

    /**
     * Returns this task's icon.
     *
     * @return This task's icon.
     */
    public String getIcon() {
        return "";
    }

    /**
     * Checks whether this task need reminder.
     *
     * @return Boolean value indicating whether the task need to be reminded.
     */
    public boolean hasReminder() {
        return hasReminder;
    }

    /**
     * Tells the time when duke remind user about the task.
     *
     * @return The time when user will be reminded of this task.
     */
    public LocalDateTime getReminderTime() {
        return reminderTime;
    }

    /**
     * Gets the standard date time formatter for tasks.
     *
     * @return The date time formatter.
     */
    public static DateTimeFormatter getDateTimeFormatter() {
        return dateTimeFormatter;
    }
}
