/**
 * Class to manage task stored in chatbot
 */
public class Task {
    /** Stores the task. */
    String task;

    /**
     * Constructor for Task.
     * @param task task to be stored
     */
    Task(String task) {
        this.task = task;
    }

    /**
     * Getter for task.
     * @return task
     */
    public String getTask() {
        return task;
    }

    @Override
    public String toString() {
        return task.toString();
    }
}
