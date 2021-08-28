package duke.task;

public class Task {
    private String description;
    private boolean isDone;

    /**
     * Creates a new Task object.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    /** Returns the status icon of the task */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done duke.task with X
    }

    /** Returns the description of the task */
    public String getDescription() {
        return this.description;
    }

    /** Mark the task as done */
    public void markAsDone() {
        this.isDone = true;
    }
}
