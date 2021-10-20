package duke.task;

/**
 * A task that can be added to the list.
 * Can be marked as done and has a description of the task.
 */
public class Task {
    protected Boolean isDone;
    private final String DATE_DESCRIPTION;

    /**
     * Constructor for Task.
     *
     * @param str Description of Task
     */
    public Task(String str) {
        isDone = false;
        DATE_DESCRIPTION = str;
    }

    /**
     * Constructor for Task.
     *
     * @param isDone String representation of task being done
     * @param str    Description of Task
     */
    public Task(String isDone, String str) {
        this.isDone = !isDone.equals("0");
        DATE_DESCRIPTION = str;
    }

    /**
     * Marks a task as done.
     */
    public void markDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? 'X' : ' ') + "] " + DATE_DESCRIPTION;

    }

    /**
     * Returns String representation for task saving.
     *
     * @return Task string to be saved
     */
    public String saveString() {
        return (isDone ? "1" : "0") + "|" + DATE_DESCRIPTION;
    }
}
