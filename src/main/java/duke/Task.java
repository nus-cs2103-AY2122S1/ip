package duke;

/**
 * Represents a task. A Task always has a description.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Class constructor that constructs a Task object.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone) ? "X" : " ";
    }
        
    public boolean isKeyword(String keyword) {
        if (this.description.contains(keyword)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Updates that the task is done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Returns a String representation of Task.
     *
     * @return String representation of Task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}