package duke;
/**
 * Encapsulates a task in a task list.
 */
public class Task {

    protected enum TaskType {
        todo, deadline, event
    }
    protected TaskType category;
    private final String description;
    private boolean completed;

    /**
     * Constructor for a Task
     *
     * @param description String description of task.
     */
    protected Task(String description) {
        this.description = description;
        this.completed = false;
    }

    /**
     * Mark a task as completed.
     */
    public void markCompleted() {
        completed = true;
    }

    /**
     * Gets the status of a task as a checkbox.
     *
     * @return A string representing status of task.
     */
    private String getStatus() {
        return "[" + (completed ? "X" : " ") + "]";
    }

    /**
     * String representation of task.
     *
     * @return String representing task.
     */
    @Override
    public String toString() {
        return getStatus() + " " + description;
    }
}
