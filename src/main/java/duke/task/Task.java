package duke.task;

/**
 * A Task is something that needs to be done.
 */
public class Task {
    protected static int idCounter = 1;
    protected int id;
    protected String description;
    protected boolean isDone;

    /**
     * Initialises a Task without description.
     */
    public Task() {}

    /**
     * Initialises a Task with a description.
     *
     * @param description the description String for the Task.
     */
    public Task(String description) {
        this.id = idCounter;
        this.description = description;
        this.isDone = false;
        idCounter++;
    }

    /**
     * Returns the status of the Task.
     * [X] means the Task is done.
     * [ ] means the Task is not done.
     *
     * @return the status icon of the Task.
     */
    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Sets a Task to be done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Returns the description of the Task.
     *
     * @return the description String of the Task.
     */
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
