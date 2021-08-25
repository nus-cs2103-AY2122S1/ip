package duke.task;

/**
 * A task that can be added to the list.
 * Can be marked as done and has a description of the task.
 */
public class Task {
    private Boolean done;
    private final String description;

    /**
     * Constructor for Task.
     *
     * @param str Description of Task
     */
    public Task(String str) {
        done = false;
        description = str;
    }

    /**
     * Constructor for Task.
     *
     * @param isDone String representation of task being done
     * @param str    Description of Task
     */
    public Task(String isDone, String str) {
        done = !isDone.equals("0");
        description = str;
    }

    public void markDone() {
        done = true;
    }

    @Override
    public String toString() {
        return "[" + (done ? 'X' : ' ') + "] " + description;

    }

    /**
     * Returns String representation for task saving.
     *
     * @return Task string to be saved
     */
    public String saveString() {
        return (done ? "1" : "0") + "|" + description;
    }
}
