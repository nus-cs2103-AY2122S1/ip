/**
 * Class to represent tasks
 *
 * @author  Yim Jaewon
 */
public class Task {

    /**
     * The description of the task.
     */
    protected String description;

    /**
     * The boolean of whether the task is done.
     */
    protected boolean isDone;

    /**
     * Constructor of the task.
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Copied from the partial solution. Get the icon of the status.
     * @return X if done and a space if not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * change the boolean value isDone to be true
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * override toString method for easier printing
     *
     * @return the stingified task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}