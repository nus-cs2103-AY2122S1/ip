package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The task object.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    protected boolean isNeedReminder;
    protected LocalDateTime reminderTime;

    /**
     * Default constructor for Task.
     */
    public Task() {
        this.description = "";
        this.isDone = false;
        isNeedReminder = false;
    }

    /**
     * Constructor for Task with String description.
     *
     * @param description Description for task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        isNeedReminder = false;
    }

    public Task(String description, String reminderTime){
        this.description = description;
        this.isNeedReminder = true;
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

    public boolean isNeedReminder() {
        return isNeedReminder;
    }

    public LocalDateTime getReminderTime() {
        return reminderTime;
    }
}
