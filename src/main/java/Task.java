/**
 * The Task class encapsulates all the details of each task.
 */
public class Task {
    private final String message;
    private Boolean completed;

    public Task(String message) {
        this.message = message;
        this.completed = false;
    }

    /**
     * Overrides toString() method.
     * @return String representation of the task object.
     */
    @Override
    public String toString() {
        return "[" + (completed ? "X" : " ")  + "] " + this.message;
    }

    /**
     * Sets the task to completed.
     */
    public void doTask() {
        this.completed = true;
    }
}
