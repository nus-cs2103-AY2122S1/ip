/**
 * The Task class encapsulates all the details of each task.
 */
public class Task {
    private String message;

    public Task(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
