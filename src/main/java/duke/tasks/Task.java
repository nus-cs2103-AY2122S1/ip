package duke.tasks;

/**
 * Class to encapsulate a Task
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Task constructor.
     *
     * @param description String description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns string representation of status, X for done and empty otherwise.
     *
     * @return String icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns string representation of status for saving, 1 for done and 0 otherwise.
     *
     * @return String icon
     */
    public String getSaveIcon() {
        return (isDone ? "1" : "0");
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * To mark a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * String for saving a task.
     *
     * @return String for saving
     */
    public String toSaveString() {
        return this.getSaveIcon() + " | " + this.description;
    }
}
