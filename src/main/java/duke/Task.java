package duke;

/**
 * Parent task class that represents a generic task
 */
public class Task {
    protected boolean isDone;
    protected String description;

    public Task() {
        this.description = null;
        this.isDone = false;
    }

    /**
     * Constructor for the task object
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getTaskIcon() {
        return "a";
    }

    /**
     * Method to retrieve the status of a task on whether it is done or not
     *
     * @return 1 if the task is done and 0 otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "1" : "0");
    }

    /**
     * Method to retrieve the description of a task
     *
     * @return The description of a task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks a task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Outputs the task as the description of the task
     *
     * @return The description of the task
     */
    @Override
    public String toString() {
        return this.description;
    }

}
