/**
 * This class encapsulates the  Task.
 *
 * @author Megan Wee Rui En
 * @version CS2103T AY21/22 Semester 1
 */

package duke.tasks;

public class Task {

    protected final String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns an 'X' if the task has been marked as done, and an " " otherwise.
     *
     * @returns the string of the task to be represented in the list
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the isDone attribute to true, which marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the string representation of the task completion.
     *
     * @returns the string representation of the task completion
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] ";
    }

    /**
     * Returns the representation of the task to be saved in the text file.
     *
     * @return an empty string
     */
    public String getStatusString() {
        return "";
    }

}

