package duke.task;

/**
 * Parent class for all types of Tasks
 * Contains basic properties and methods that all Tasks should have
 */
public abstract class Task {
    /** Name of the task **/
    private String name;

    /** Whether the task is completed or not **/
    private boolean isDone = false;

    /**
     * Initializes a new task
     *
     * @param name Name of task
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Initializes a new task with the done property
     *
     * @param name Name of task
     * @param isDone Whether task is done
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
