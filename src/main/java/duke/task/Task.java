package duke.task;

/**
 * A Task with ability to mark as done.
 *
 * @author KelvinSoo
 */
public abstract class Task {
    private boolean isDone;
    private final String description;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String getDescription() {
        return description;
    }

    public String getMetaData() {
        return String.format("%s|%s", isDone ? "X" : " ", description);
    }

    public void markAsDone() {
        isDone = true;
    }
}
