package dino.task;

/**
 * Represents a piece of work to be done or undertaken
 */
public class Task {

    private String description;
    private boolean isDone;

    /**
     * Constructs a Task object
     * Initializes the task status to be undone
     *
     * @param description the description for the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone() {
        isDone = true;
    }

    public boolean getStatus() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Displays the task in the format "| Status | Description"
     *
     * @return the task in the required format
     */
    @Override
    public String toString() {
        return " | " + (isDone ? "1" : "0") + " | "
                + description;
    }

}
