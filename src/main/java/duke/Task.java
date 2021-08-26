package duke;

/**
 * A Task object.
 *
 * @author Erwin Quek
 * @version CS2103 AY21/22 Sem 1
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Task constructor.
     * @param description Takes in a description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Method to get the status of the task if it is done or not.
     * @return "X" if its done and " " if not done
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
     * Method to check task has been marked
     * @return "1" or "0" value to represent marked and unmarked respectively
     */
    public String getZeroOrOne() {
        return (isDone ? "1" : "0");
    }

    /**
     * Method to get info of the task to be written in a file
     * @return A string representation to the task description
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