package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * A base class for all the task which can be put into the TaskList.
 */
public class Task {
    protected String taskType;
    protected String description;
    protected boolean isDone;
    protected LocalDateTime time;
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");

    /**
     * Construct an instance of Task with only a description.
     * Automatically sets isDone to false.
     *
     * @param description Description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Construct an instance of Task with only a description and a taskType.
     *
     * @param description Description of the task.
     * @param taskType Type of the task (event, deadline, etc.)
     */
    public Task(String description, String taskType) {
        this(description);
        this.taskType = taskType;
    }

    /**
     * Construct an instance of Task with description, taskType and a time.
     *
     * @param description Description of the task.
     * @param taskType Type of the task (event, deadline, etc.)
     * @param time The time associated with the task.
     */
    public Task(String description, String taskType, LocalDateTime time) {
        this(description, taskType);
        this.time = time;
    }

    /** Returns 'X' if the task is mark as done, a black space if not **/
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Changes the isDone of this task to the boolean input.
     *
     * @param t boolean input for the isDone property to follow.
     */
    public void markFinished(boolean t) {
        this.isDone = t;
    }

    /**
     * Returns the taskType of the task.
     *
     * @return the task type.
     */
    public String getTaskType() {
        return taskType;
    }

    /**
     * Returns the description of the task.
     *
     * @return the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the time associated to the task.
     *
     * @return the time in the format of yyyy-MM-dd HH:mm.
     */
    public String getTime() {
        return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Update the description of the task.
     *
     * @param desc The new description.
     */
    public void update(String desc) {
        this.description = desc;
    }

    /**
     * Update the description of the task and the time of the task.
     *
     * @param desc The new description.
     * @param date The new time.
     */
    public void update(String desc, LocalDateTime date) {
        this.description = desc;
        this.time = date;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", taskType, getStatusIcon(), getDescription());
    }
}
