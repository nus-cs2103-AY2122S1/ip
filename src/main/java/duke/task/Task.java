package duke.task;

/**
 * Represents a task that can be marked as completed/done.
 */
public abstract class Task implements Comparable<Task> {
    /**
     * Description or name of the task.
     */
    private final String description;

    /**
     * The completion status of the task.
     *
     * True if the task is already completed.
     */
    private boolean completed;

    Task(String description, boolean completed) {
        this.description = description;
        this.completed = completed;
    }

    /**
     * Provides the task description.
     * @return task descrption.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Provides the completion status of the task.
     * @return Completion status of task.
     */
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
     * Encodes the duke.tasks.Task into a String that can be stored.
     *
     * @return A one-line String containing the data of the duke.tasks.Task
     */
    public abstract String encode();
}
