package duke.tasks;

/**
 * Represents a Task object.
 *
 * @author Erwin Quek
 * @version CS2103 AY21/22 Sem 1
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Task constructor.
     * @param description Takes in a description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status of the task if it is done or not.
     * @return "X" if its done and " " if not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Method to mark task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns 1 if the task has been marked and 0 if unmarked.
     * @return "1" or "0" value.
     */
    public String getZeroOrOne() {
        return (isDone ? "1" : "0");
    }

    /**
     * Returns the task info which will be used to write in a duke.txt.
     * @return A string representation to the task description.
     */
    public String getTaskInfo() {
        return getZeroOrOne() + "|" + description;
    }

    /**
     * A method to have a String representation of task object
     * @return A string of the task's details
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " +  this.description;
    }
}