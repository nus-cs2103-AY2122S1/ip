package duke.task;

import java.time.LocalDateTime;

import duke.util.DukeException;

/**
 * Abstract class task contains fundamental attributes such as task name
 * and whether task is completed.
 */
public abstract class Task {
    /** Task name */
    private String taskName;
    /** Indicates if task is complete*/
    private boolean isDone;

    /**
     * Task constructor initialises Task with completed status and
     * task name.
     *
     * @param isDone task is completed.
     * @param taskName name of task.
     */
    public Task(boolean isDone, String taskName) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    public String getTaskName() {
        return taskName;
    }

    /**
     * Sets done to true.
     */
    protected void markAsDone() {
        this.isDone = true;
    }

    /**
     * Transform a task into a concise string that contains information about the task.
     *
     * @return concise string representation of task to be saved into memory
     */
    protected String encode() {
        return isDone
            ? String.format("1|%s", this.taskName)
            : String.format("0|%s", this.taskName);
    }

    /**
     * Turn task into a human readable string form.
     *
     * @return human readable string representation of task.
     */
    @Override
    public String toString() {
        return isDone
            ? String.format("[X] %s", this.taskName)
            : String.format("[ ] %s", this.taskName);
    }

    /**
     * Changes the task name.
     *
     * @param newName name to be changed to.
     */
    public void setName(String newName) {
        this.taskName = newName;
    }

    /**
     * Changes the datetime information of the task if possible.
     *
     * @param dateTime name to be changed to.
     * @throws DukeException if task is a has no date time attribute.
     */
    public abstract void setDateTime(LocalDateTime dateTime) throws DukeException;
}
