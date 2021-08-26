package duke;

/**
 * An abstract class encapsulating a Task.
 *
 * @author Toh Wang Bin
 */
public abstract class Task {

    //A description of the task
    String name;
    //A boolean signifying if the task has been completed
    boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
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
