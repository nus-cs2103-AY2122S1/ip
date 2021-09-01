package bobcat.model.task;

public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Returns a <code>Task</code> object which represents a task in a TaskList
     * @param entry description of task
     * @param isComplete status whether task is completed or not
     */
    public Task(String entry, boolean isComplete) {
        this.description = entry;
        this.isDone = isComplete;
    }
    public Task(String entry) {
        this(entry, false);
    }

    public void markDone() {
        isDone = true;
    }

    public void markIncomplete() {
        isDone = false;
    }

    private String getStatus() {
        return isDone ? "X" : " ";
    }

    public String toString() {
        return "[" + this.getStatus() + "] " + this.description;
    }
}
