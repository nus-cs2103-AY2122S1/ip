/**
 * Encapsulates a task containing a description and status.
 */
public class DukeTask {
    private String description;
    private boolean isDone;

    /**
     * Constructor to instantiate a `DukeTask`.
     * The isDone field is set to false by default as new tasks should not be done yet.
     *
     * @param description describes what the task is
     */
    public DukeTask(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Method to mark a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    private String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Formats the task in string form, displaying the task status and description.
     *
     * @return the task in a displayed string format
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
