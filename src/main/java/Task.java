/**
 * The Task class encapsulates all the details of each task.
 */
public class Task {
    private String message;

    public Task(String message) {
        this.message = message;
    }

    /**
     * Overrides toString() method.
     * @return String representation of the task object.
     */
    @Override
    public String toString() {
        return this.message;
    }
}
