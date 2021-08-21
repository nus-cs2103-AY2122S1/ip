package lifeline.task;

/**
 * The Task class represents a task
 */
public class Task {

    /**
     * Name of task
     */
    private String name;

    /**
     * Indicates whether the task has been completed
     */
    private boolean isDone;

    /**
     * Default constructor for a Task. A new task is not done by default.
     *
     * @param name Name of task
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Constructor to be used when loading saved tasks
     *
     * @param name Name of task
     * @param isDone Indicates whether task is completed
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }
}