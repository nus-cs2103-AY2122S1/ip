package duke;

/**
 * An abstract class encapsulating a Task.
 *
 * @author Toh Wang Bin
 */
public abstract class Task {

    /**
     * The variants of the Tasks handled by Duke
     */
    enum Tasks { DEADLINE, EVENT, TODO }

    //A description of the task
    private String name;
    //A boolean signifying if the task has been completed
    private boolean isDone;

    /**
     * Constructor for a Task instance.
     *
     * @param name A description of the Task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Getter for the name of a Task.
     *
     * @return A string.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the isDone field of a Task.
     *
     * @return
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets the Task instance as completed.
     */
    public void setCompleted() {
        this.isDone = true;
    }

    /**
     * Returns the String representation of the Task instance.
     *
     * @return A String representing the Task instance.
     */
    public abstract String toString();

    /**
     * Returns a string representation of the Task instance optimised for
     * saving in the file.
     *
     * @return A String representing the Task instance.
     */
    public abstract String toDataString();

}
