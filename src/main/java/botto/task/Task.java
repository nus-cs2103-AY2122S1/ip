package botto.task;

/**
 * Format for the Botto bot's task
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a task
     *
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * get the status icon of the task
     *
     * @return "X" if the task is done, else " "
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * mark the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }


    /**
     * return the task description
     *
     * @return the task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * return string representation of the task
     *
     * @return string representation of the task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
