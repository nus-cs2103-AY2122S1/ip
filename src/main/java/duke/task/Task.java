package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The task object.
 */
public abstract class Task {
    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
    private static final DateTimeFormatter outputDateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private String description;
    private boolean isDone;
    private boolean hasReminder;
    private LocalDateTime reminderTime;

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
     * Gets the standard date time formatter for tasks.
     *
     * @return The date time formatter.
     */
    public static DateTimeFormatter getDateTimeFormatter() {
        return dateTimeFormat;
    }

    /**
     * Gets the standard date time formatter to print in front of users.
     *
     * @return Date time format that is going to be shown to the user.
     */
    public static DateTimeFormatter getOutputDateTimeFormatter() {
        return outputDateTimeFormat;
    }

    /**
     * Returns this task's icon.
     *
     * @return This task's icon.
     */
    public abstract String getIcon();

    /**
     * Shows the time that the task will happen.
     *
     * @return The task's deadline or data.
     */
    public abstract String getTaskTime();

    /**
     * Marks this task as done.
     */
    public void markTaskAsDone() {
        this.isDone = true;
    }

    /**
     * Returns task status.
     *
     * @return Integer with 1 indicating task has been done and 0 has not been done.
     */
    public int getDoneStatus() {
        return isDone ? 1 : 0;
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

    @Override
    public String toString() {
        String taskType = "[" + getIcon() + "]";
        String doneStatus = isDone ? "[X]" : "[ ]";
        String description = " " + getDescription();
        return taskType + doneStatus + description;
    }
}
