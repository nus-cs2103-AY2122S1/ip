/**
 * An abstract class encapsulating a Task.
 *
 * @author Toh Wang Bin
 */
public abstract class Task {

    String name;
    boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Set the Task instance as completed.
     */
    public void setCompleted() {
        this.isDone = true;
    }

    /**
     * Return the String representation of the Task instance.
     *
     * @return A String representing the Task instance.
     */
    public abstract String toString();

    public abstract String toDataString();

}
