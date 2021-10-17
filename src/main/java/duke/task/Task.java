package duke.task;

import java.time.LocalDate;

import duke.CommandList;

/**
 * duke.task.Task contains information about a task.
 */
public abstract class Task {
    private String value = null;
    private boolean isDone = false;
    private LocalDate time = null;

    /**
     * Task constructor
     *
     * @param value String input from user.
     */
    public Task(String value) {
        this.value = value;
        this.isDone = false;
    }

    public abstract LocalDate getTime();

    public abstract CommandList getType();

    public boolean isDone() {
        return isDone;
    }

    /**
     * Getting the information of the task.
     *
     * @return Information of the task.
     */
    public String getValue() {
        return value;
    }

    /**
     * Mark task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Mark task as undone.
     */
    public void markUnDone() {
        this.isDone = false;
    }

    /**
     * Get the status icon for this task.
     *
     * @return A string representing the state of the task.
     */
    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + value;
    }

}
