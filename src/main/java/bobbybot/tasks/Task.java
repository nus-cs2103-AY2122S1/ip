package bobbybot.tasks;

/**
 * Represents a task that can be completed
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     *
     * @param description description of task
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Getter for isDone status
     * @return "X" or " "
     */
    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Getter for string in save-friendly format
     */
    public String getSaveFormatString() {
        int isDoneInt = isDone ? 1 : 0;
        return ("T," + isDoneInt + "," + description);
    }

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + description;
    }
}
