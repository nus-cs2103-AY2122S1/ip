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

    /**
     * Get the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", this.description);
    }

    public String toSaveFormat() { return String.format("%s,%s", isDone ? "1" : "0", this.description); }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Task)) {
            return false;
        }

        // obj is an instance of Task, so it is okay to typecast here
        Task other = (Task) obj;

        return this.description.equals(other.description) && this.isDone == other.isDone;
    }
}
