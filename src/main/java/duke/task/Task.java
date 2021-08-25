package duke.task;

/**
 * Abstract class representing Task that can be added to the TaskList.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Class constructor which receives a description describing the Task, initializes with isDone as false.
     *
     * @param description Description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Method which marks Task as done by changing the isDone field to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Method which returns a String with the description of the Task and whether it has been done.
     *
     * @return String representation of the Task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Method which returns a String with the representation of the Task to be written to the data file.
     *
     * @return String representation of the Task for the data file.
     */
    public String toFile() {
        return "| " + (this.isDone ? 1 : 0) + " | " + this.description;
    }

    /**
     * Method which returns the description of the Task.
     *
     * @return String Description of the Task.
     */
    public String getDescription() {
        return this.description;
    }
}
