package duke;

/**
 * This class encapsulates a task in the Duke application.
 */
public abstract class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the Task instance as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", this.description);
    }

    public String toSaveFormat() { return String.format("%s,%s", isDone ? "1" : "0", this.description); };
}
