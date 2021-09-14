package duke;

/**
 * This class simulates a real life task to be completed and
 * its status of completion. May contain date and time depending on the
 * type of task.
 */
public class Task {
    /** The description of the task. **/
    protected String description;

    /** Indicates if the task has been completed. **/
    protected boolean isDone;

    /** Indicates if the task has been saved in a file. **/
    protected boolean wasSaved;

    /**
     * Constructor for a Task.
     *
     * @param description Description of the task.
     */
    public Task (String description) {
        this.description = description;
        this.isDone = false;
        this.wasSaved = false;
    }

    /**
     * Returns the description of the current task.
     *
     * @return Description of the current task.
     */
    public String getDescription() throws IllegalArgumentException {
        if (this.description.contains("deadline") || this.description.contains("event")) {
            throw new IllegalArgumentException();
        } else {
            return this.description;
        }
    }

    /**
     * Marks the current task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Returns the string representation of status of completion of the task.
     *
     * @return String representation of status of completion of the task.
     */
    public String getStatusIcon() {
        return (this.isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns the string representation of the task and its completion status.
     *
     * @return String representation of the task and its completion status.
     */
    public String printTask() {
        return this.getStatusIcon() + " " + this.getDescription();
    }

    /**
     * Indicates the current task as being saved in the file (using the filepath given).
     */
    public void markSaved() {
        this.wasSaved = true;
    }
}
