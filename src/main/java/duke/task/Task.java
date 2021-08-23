package main.java.duke.task;

import java.time.LocalDate;

/**
 * A task contains its description and information of whether it is done or not.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S1
 */
public abstract class Task {
    protected final String DESCRIPTION;
    private boolean isDone;

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
    public void setDone() {
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

    /**
     * Returns if the task date is equal to the date provided.
     *
     * @param date the date provided
     * @return true if they are both equal
     */
    public abstract boolean onDate(LocalDate date);

    /**
     * Returns true if the task's description contains the string
     * @param str the substring to be searched
     * @return true if the task's description contains the string
     */
    public boolean containString(String str) {
        return DESCRIPTION.contains(str);
    }
}
