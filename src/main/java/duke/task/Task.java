package duke.task;

/**
 * This class encapsulates a task in the Duke application.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for a new Task, to be called by its subclasses.
     *
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for a new Task, to be called by its subclasses.
     *
     * @param description The task description.
     * @param isDone      Whether this task is marked as done.
     */
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
     * Gets the description of the task.
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

    /**
     * Converts the task to the save format.
     *
     * @return Task in the save format as a string.
     */
    public String toSaveFormat() {
        return String.format("%s,%s", isDone ? "1" : "0", this.description);
    }

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
