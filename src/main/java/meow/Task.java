package meow;

/**
 * Represents a general task that the user wants to add
 * in his or her todo list.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * A public constructor to initialize a meow.Task object.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "1" : "0"); // mark done task with X
    }

    /**
     * Returns the description of the meow.Task object.
     *
     * @return The description of the meow.Task object.
     */
    public String getDescription() {
        return description; // mark done task with X
    }

    /**
     * Changes the isDone field of the meow.Task object to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the string representation of the meow.Task object.
     *
     * @return The string representation of the meow.Task object.
     */
    @Override
    public String toString() {
        return " | " + getStatusIcon() + " | " + getDescription();
    }
}
