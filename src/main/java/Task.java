package main.java;

/**
 * A task contains its description and information of whether it is done or not.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S2
 */
public abstract class Task {
    private final String DESCRIPTION;
    private boolean isDone = false;

    /**
     * Constructor for task.
     *
     * @param description the description
     */
    public Task(String description, boolean isDone) {
        this.DESCRIPTION = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return status icon
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Change task to done.
     */
    protected void setDone() {
        isDone = true;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.DESCRIPTION;
    }
}
