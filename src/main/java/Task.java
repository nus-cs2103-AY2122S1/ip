/**
 * Abstract Class to manage task stored in chatbot.
 *
 * @author marcuspeh
 * @version Level-4
 * @since 15 Aug 2021
 */
public abstract class Task {
    /** Stores the task. */
    private String task;
    /** Stores if the task is done. */
    private boolean done;

    /**
     * Constructor for Task.
     *
     * @param task task to be stored
     */
    Task(String task) {
        this.task = task;
        this.done = false;
    }

    /**
     * Marks the task as done.
     * If task is successfully marked as done, true will be returned.
     * If task is not successfully marked as done, fasle will be returned instead.
     *
     * @return if update is successful.
     */
    public boolean markDone() {
        if (done)
            return false;
        done = true;
        return true;
    }

    /** Checks if task is done or not. */
    public boolean isDone() {
        return done;
    }

    /**
     * Getter for task.
     *
     * @return task
     */
    public String getTask() {
        return task;
    }

    @Override
    public String toString() {
        return (done ? "[X] " : "[ ] ") + task.toString();
    }
}
