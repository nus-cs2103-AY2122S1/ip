public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Public constructor of a task.
     *
     * @param description The desceiption of the task. */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the representation of whether a task is done.
     *
     * @return Character that represents whether a task is done. */
    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /** Marks the tasks to be done. */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
