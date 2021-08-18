/**
 * Represents a task that can be marked as completed/done.
 */
public class Task {
    private final String description;
    private boolean completed;

    Task(String description, boolean completed) {
        this.description = description;
        this.completed = completed;
    }

    /**
     * Marks the task as completed.
     */
    public void complete() {
        this.completed = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", completed ? "X" : " ", description);
    }
}
