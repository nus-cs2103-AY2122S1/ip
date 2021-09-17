package duke.tasks;

/**
 * The Task class encapsulates a task.
 */
public abstract class Task {
    /** Name of the task */
    private String taskName;

    /** Whether the task is completed or not */
    private boolean isCompleted;

    /**
     * Creates a task instance with a task name.
     *
     * @param taskName Name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
        assert !taskName.equals("") : "task name should not be empty";
    }

    /**
     * Mark the task as completed.
     */
    public void markAsCompleted() {
        this.isCompleted = true;
    }

    /**
     * Returns whether the task is completed or not.
     *
     * @return Boolean value of whether the task is completed.
     */
    public boolean getCompleted() {
        return this.isCompleted;
    }

    /**
     * Returns the name of the task.
     *
     * @return A string of the name of the task.
     */
    public String getTaskName() {
        return this.taskName;
    }

    @Override
    public String toString() {
        return this.taskName + " " + (this.isCompleted ? "[X]" : "[ ]");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            return ((Task) obj).taskName.equals(this.taskName);
        }
        return false;
    }
}
