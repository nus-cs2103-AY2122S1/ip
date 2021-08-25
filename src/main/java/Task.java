/**
 * Represents a task that can be marked as completed/done.
 */
public abstract class Task {
    private final String description;
    private boolean completed;

    Task(String description, boolean completed) {
        this.description = description;
        this.completed = completed;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
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

    /**
     * Encodes the Task into a String that can be stored.
     *
     * @return A one-line String containing the data of the Task
     */
    public abstract String encode();
}
