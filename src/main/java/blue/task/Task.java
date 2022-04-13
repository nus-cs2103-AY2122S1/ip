package blue.task;

/**
 * Holds the similarity of all tasks.
 */
public abstract class Task {
    private final String title;
    private boolean isDone;

    public Task(String title) {
        this(title, false);
    }

    /**
     * Constructs a Task.
     *
     * @param title  Title of task.
     * @param isDone Whether the task is done.
     */
    public Task(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    public String getTitle() {
        return title;
    }

    public void markDone() {
        isDone = true;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns a String representation of this instance.
     *
     * @return String representation of this instance.
     */
    @Override
    public String toString() {
        String taskStatus = isDone ? "X" : " ";
        return "[" + taskStatus + "] " + title;
    }
}
