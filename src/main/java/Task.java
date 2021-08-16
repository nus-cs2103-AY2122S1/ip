public class Task {
    private boolean isDone = false;
    private final String title;

    /**
     * Constructor of Task. Create a new task with the given title.
     *
     * @param title Title of Task
     */
    public Task(String title) {
        this.title = title;
    }

    /**
     * If task is done, return "X" else " "
     *
     * @return String "X" or " "
     */
    private String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns whether the task is done.
     *
     * @return Boolean true if the task is done, false otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Mark the Task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Return a String format of the Task containing the status and title of task.
     *
     * @return String representation of Task
     */
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), title);
    }
}
