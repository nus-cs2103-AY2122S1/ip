/**
 * A Task with ability to mark as done.
 *
 * @author KelvinSoo
 */
public class Task {
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

    public void markAsDone() {
        isDone = true;
    }
}
