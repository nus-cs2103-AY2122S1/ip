package duke.tasks;

/**
 * Class that initializes a task
 *
 */
public class Task {

    /** String for description of the task */
    protected String description;

    /** Boolean that states if the task is done or not */
    protected boolean isDone;

    /**
     * Constructor for the task class
     *
     * @param description String for the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Method that sets the respective task to be done
     *
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Method that returns whether the task has been done or not
     *
     * @return String "X" if task is done and " " if it is not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Method that returns the string representation of
     * the task data to be written into the storage file
     *
     * @return String representation of data to be written into storage file
     */
    public String getFileString() {
        int i = this.isDone ? 1 : 0;
        return "T | " + i + " | " + this.description;
    }

    /**
     * Method that returns the string representation of
     * the task data to be outputted
     *
     * @return String representation of data to be outputted
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}