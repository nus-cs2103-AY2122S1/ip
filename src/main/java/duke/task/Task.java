package duke.task;

/**
 * Represents the various types of tasks that can be added to the task list.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Represents a constructor for the Command class where the user command is initialized.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks if the task has been completed or not.
     *
     * @return Either X or an empty string depending on whether the task is completed or not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }
    
    /**
     * Denotes the string representation of the task list that is stored on duke.txt.
     * 
     * @return String representation of task list stored on duke.txt.
     */
    public abstract String getTaskListOnDisk();
    
    /**
     * Returns the string representation of the task that is stored in the task list.
     *
     * @return String description of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }
}
