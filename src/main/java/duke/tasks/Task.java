package duke.tasks;

/**
 * Class that represents a Task that has a description and can be completed
 */
public class Task {

    /** The description of the task */
    private String description;

    /** A boolean representing if the task is complete */
    private Boolean isCompleted;

    /**
     * A constructor for a Task
     *
     * @param description A String describing the task.
     */
    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    /**
     * An alternate constructor for a Task that
     * specifies if it has been completed or not.
     *
     * @param description A String description of the Task
     * @param isComplete A boolean indicating if the Task is complete
     */
    public Task(String description, Boolean isComplete) {
        this.description = description;
        this.isCompleted = isComplete;
    }

    /**
     * Sets the calling Task object to complete.
     */
    public void completeTask() {
        this.isCompleted = true;
    }

    /**
     * Returns the completion status of the calling Task.
     *
     * @return True if the task is completed and false otherwise
     */
    public Boolean isCompleted() {
        return this.isCompleted;
    }

    /**
     * Gets the description of the calling Task.
     *
     * @return The String description of the calling Task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns an easily parsable String representing the calling Task
     * for use inside persistent storage.
     *
     * @return A String representing the Task in an easily parsable format.
     */
    public String getFileRepr() {
        return " | " + (this.isCompleted ? "1" : "0") + " | " + this.description;
    }

    /**
     * Returns a String representation of the calling Task that can be displayed
     * to the user for use within the Duke UI.
     *
     * @return A user-friendly String representation of the calling Task
     */
    @Override
    public String toString() {
        if (this.isCompleted) {
            return ("[X] " + this.description);
        } else {
            return ("[ ] " + this.description);
        }
    }
}
