package duke.task;

/**
 * Represents the tasks that Duke can keep track of.
 *
 * @author Javier Phon Zhee Kai.
 * @version CS2103T AY21/22 Sem 1.
 */
public class Task {

    /** A string representing the description of a task. */
    protected String description;

    /** A boolean representing the completion status of a task. */
    protected boolean isDone;

    /**
     * Constructor of the Task class
     *
     * @param description A string representing the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a either a "X" or a white space to indicate task completion status.
     *
     * @return "X" if task is completed and a " " if task is not completed.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the description of a task.
     *
     * @return A string representing the description of a task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the status of a task.
     *
     * @param done A boolean representing if the task is completed.
     */
    public void setIsDone(boolean done) {
        this.isDone = done;
    }

    /**
     * Returns if a task is completed
     *
     * @return True if the task is completed and false otherwise.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns a string that is used to represent tasks when saved to a file.
     *
     * @return A string representation of a task for saving to files.
     */
    public String saveToFile() {
        return "| " + (getIsDone() ? "1" : "0") + " | " + description;
    }

    /**
     * Returns a string that is used to represent tasks when Duke is interacting with a user.
     *
     * @return A string representation of a task to be displayed to users.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
