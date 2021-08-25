package duke.task;

/**
 * General tasks to be completed by the user with no time property.
 */
public class Task {
    private boolean isDone;
    private String taskName;

    /**
     * Public constructor which creates a new Task object.
     *
     * @param taskName The description of the Event.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Public constructor which creates a new Task object that has
     * been done.
     * Used for creation of tasks which may have been completed in
     * the previous session but not deleted.
     *
     * @param taskName The description of the Event.
     * @param isDone Whether the task has been completed.
     */
    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    /**
     * Gets the string representation of the completion status of the task.
     *
     * @return The string representation of the status, "X" for completed and " "
     *          for incomplete.
     */
    public String getStatus() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Marks the task as completed.
     */
    public void complete() {
        this.isDone = true;
    }

    /**
     * Gets the description of the task
     *
     * @return Description of the task
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Gets the completion status of the task.
     *
     * @return Whether a task has been completed.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Gets the string representation of the Task object.
     *
     * @return The string representation of the Task object.
     */
    public String displayInfo() {
        return String.format("[T] [%s] %s", this.getStatus(), this.getTaskName());
    }

    /**
     * Gets the string representation of the Task object,
     * used for writing into the local file system.
     *
     * @return The string representation of the task object,
     *          used for writing into the local file system.
     */
    public String getSaveInfo() {
        if (this.isDone()) {
            return String.format("T | 1 | %s", this.getTaskName());
        } else {
            return String.format("T | 0 | %s", this.getTaskName());
        }
    }

}
