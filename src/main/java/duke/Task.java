package duke;

/**
 * Represents base class for all tasks that can be marked as done.
 */
public abstract class Task implements Cloneable {
    protected String description;
    protected boolean isDone;

    /**
     * @param description Task description, must not be empty.
     * @throws DukeException if description is empty.
     */
    public Task(String description) throws DukeException {
        if (description.equals("")) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        this.description = description;
        this.isDone = false;
    }

    public abstract Task clone();

    /**
     * Marks Task as done.
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
