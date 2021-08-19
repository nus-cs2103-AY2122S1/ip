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
    private boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param task task to be stored
     */
    Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    /**
     * Constructor for Task
     *
     * @param task Task to be stored
     * @param done Whether the task is done
     */
    Task(String task, boolean done) {
        this.task = task;
        this.isDone = done;
    }

    /**
     * Marks the task as done.
     * If task is successfully marked as done, true will be returned.
     * If task is not successfully marked as done, fasle will be returned instead.
     *
     * @return if update is successful.
     */
    public boolean markDone() {
        if (isDone)
            return false;
        isDone = true;
        return true;
    }

    /**
     * Abstract method for the task to return an output to save to the txt file.
     * Format is as follow: <Type(T/D/E)> <Description> <Done> <DateTime/Deadtime if applicable>
     *
     * @return string to save the txt file
     */
    public abstract String saveOutput();

    /** Getter for isDone. */
    public boolean getIsDone() {
        return isDone;
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
        return (isDone ? "[X] " : "[ ] ") + task.toString();
    }
}
