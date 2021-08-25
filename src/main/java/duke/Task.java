package duke;

/**
 * Base class for all tasks that can be marked as done.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * @param description Task description, must not be empty
     * @throws DukeException
     */
    public Task(String description) throws DukeException {
        if (description.equals("")) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        this.description = description;
        this.isDone = false;
    }

    /**
     * Mark Task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public abstract TaskType getType();
}
