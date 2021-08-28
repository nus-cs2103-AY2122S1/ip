package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public boolean getDoneStatus() {
        return isDone;
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns the task's details in a String.
     *
     * @return Task's details in a String.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns task's details in a format that will be stored in the data file.
     *
     * @return Task's details in a format that will be stored in the data file.
     */
    public String toStringData() {
        return "|" + getStatusIcon() + "|" + description;
    }
}
