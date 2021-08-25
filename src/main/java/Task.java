public class Task {
    private boolean isDone = false;
    private final String title;

    /**
     * Constructor of Task. Create a new task with the given title.
     *
     * @param title Title of Task
     */
    public Task(String title) {
        this.title = title.trim();
    }

    public Task(String title, boolean isDone) {
        this.title = title.trim();
        this.isDone = isDone;
    }

    /**
     * If task is done, return "X" else " "
     *
     * @return String "X" or " "
     */
    private String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    private int convertIsDoneToInt() {
        return isDone ? 1 : 0;
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
    public boolean markAsDone() {
        if (isDone) {
            return false;
        } else {
            isDone = true;
            return true;
        }
    }

    public String toFileString() {
        return String.format("%d | %s", convertIsDoneToInt(), title);
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
