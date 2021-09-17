package duke;

/**
 * Represents a task when the user creates a new task.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String deadline = "";

    /**
     * A constructor to create a Task object.
     *
     * @param description A description about the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns either "X" for marked tasks or " " for unmarked tasks.
     *
     * @return Either "X" or " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns "Done" for marked tasks and "NotDone" for unmarked tasks.
     *
     * @return The status of a task.
     */
    public String getStatus() {
        return isDone ? "Done" : "NotDone";
    }

    /**
     * Returns the description of a task.
     *
     * @return The description of a task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the deadline of a task.
     *
     * @return The deadline of a task.
     */
    public String getDeadline() {
        return this.deadline;
    }

    /**
     * Sets isDone to true.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns the string representation of a task description.
     *
     * @return The string representation of a task description.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
