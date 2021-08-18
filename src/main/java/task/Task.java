package task;

/**
 * Task class.
 *
 * This abstract class acts as a base for all tasks handled by Duke.
 */
public abstract class Task {

    private String description;

    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Sets isDone to true.
     */
    public void markAsDone() {
        setDone(true);
    }

    /**
     * Generates a String icon whether task class is done or not.
     *
     * @return String icon based on done status
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    @Override
    public String toString() {
        return getStatusIcon() + description;
    }
}
