package duke.task;

import java.time.LocalDateTime;

/**
 * Represents the various types of tasks that can be added to the task list.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Represents a constructor for the Task class where variables are initialized.
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

    public abstract LocalDateTime getDate();
    
    public abstract String getDescription();

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Checks if the task is done or not.
     * 
     * @return true or false.
     */
    public boolean isDone() {
        return this.isDone;
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
