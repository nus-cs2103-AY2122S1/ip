/**
 * Represents a task. Contains the description and state of the task.
 * Allows for changing of the state of the class.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for Task. Takes in the description of the task,
     * and sets the task as not done.
     *
     * @param description The description of the class.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Change the status of the Task to true
     */
    public void doneTask() {
        this.isDone = true;
    }

    /**
     * Returns the string representation of the Task.
     *
     * @return String containing description and status indicator for Task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }


}
