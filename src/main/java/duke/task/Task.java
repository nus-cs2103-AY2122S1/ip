package duke.task;

import java.time.LocalDate;

/**
 * Base class for all task classes. Contains a description of the task and a boolean value indicating
 * if the task is completed.
 */
public class Task {
    /* The description for the task */
    protected String description;
    /* The completion status of the task */
    protected boolean isDone;
    /* The date for the task */
    protected LocalDate date;
    /* The type of task */
    private final TaskType taskType;
    /**
     * Creates a task class with the given description and the completion status.
     *
     * @param description Description of the task.
     * @param isDone      Completion status of the task.
     */
    public Task(TaskType taskType, String description, LocalDate date, boolean isDone) {
        this.taskType = taskType;
        this.description = description;
        this.isDone = isDone;
        this.date = date;
    }

    /**
     * Returns the description of the task.
     *
     * @return the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the completion status of the task to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns true if the task has been marked as done or else false.
     *
     * @return A boolean indicating if the task has been marked as done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the date of the task. This value can be null if the task is a Todo.
     *
     * @return The date of the task.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the date of the task.
     *
     * @param date The date of the task to set to.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Sets the description of the task.
     *
     * @param description The description of the task to set to.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the type of this task as a String.
     * @return The type of this task.
     */
    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * Returns the string representation of the task meant to be written to the data file.
     *
     * @return the string representation of the task to be written to the data file.
     */
    public String toDataString() {
        return String.format("T | %d | %s", isDone ? 1 : 0, this.description);
    }

}
