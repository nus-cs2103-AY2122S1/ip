/**
 * Represents a task which can be marked done.
 * 
 * @author Gordon Yit
 * @Since 23-08-21
 */

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Class constructor for Task class.
     * Sets isDone to false, meaning task is not done.
     * 
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * 
     * @return "X" if task is done, else returns " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Prints out the task.
     * 
     * @return string format of the task, 
     * consisting of the status icon and task description.
     */
    @Override
    public String toString() {
        return String.format("%s | %s", getStatusIcon() == " " ? 1 : 0, description);
    }

    /**
     * Returns task marker. 
     *
     * @return a one character string that is a marker for this task.
     */
    public String getTaskMarker() {
        return "";
    }

}
