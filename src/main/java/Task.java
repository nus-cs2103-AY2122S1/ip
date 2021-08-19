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
     * @param name Name of task
     */
    public Task(String name) {
        this.name = name;
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
