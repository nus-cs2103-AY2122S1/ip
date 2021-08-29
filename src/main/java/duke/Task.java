package duke;

/**
 * This class simulates a real life task to be completed and
 * its status of completion.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected boolean wasSaved;

    /**
     * Constructor for a Task.
     * @param description the description of the task.
     */
    public Task (String description) {
        this.description = description;
        this.isDone = false;
        this.wasSaved = false;
    }

    /**
     * Returns the description of the current task.
     * @return the description of the current task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the current task as done.
     */
    public void markedDone() {
        this.isDone = true;
    }

    /**
     * Returns the string representation of status of completion of the task.
     * @return the string representation of status of completion of the task.
     */
    public String getStatusIcon() {
        return (this.isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns the string representation of the task and its completion status.
     * @return the string representation of the task and its completion status
     */
    public String printTask() {
        return this.getStatusIcon() + " " + this.getDescription();
    }

    public void markedSaved() {
        this.wasSaved = true;
    }
}
