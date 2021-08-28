package duke.task;

import java.io.Serializable;

/**
 * A serializable item that contains a description and a boolean isDone.
 */
public class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    /**
     * Initialises a task.
     *
     * @param description description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        char statusIcon = this.isDone ? 'X' : ' ';
        return String.format("[%c] %s", statusIcon, this.description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Task)) {
            return false;
        }
        Task task = (Task) o;
        return isDone == ((Task) o).isDone
                && description.equals(task.description);
    }
}