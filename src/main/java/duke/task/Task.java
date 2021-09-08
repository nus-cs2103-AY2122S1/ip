package duke.task;

/**
 * Task class that handles the common traits of the different types of tasks.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructs the task object.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return String status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Sets the status of the task.
     *
     * @param done Integer representation of status.
     */
    public void setDone(int done) {
        isDone = done == 1;
    }


    /**
     * Checks whether description contains query string.
     *
     * @param query Query string to check for in description.
     * @return Whether description contains query string.
     */
    public boolean containsQuery(String query) {
        assert query != null : "Query to check is null";
        return description.contains(query);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    /**
     * Returns the String representation of task in file format.
     * File format is the format used to save tasks in a file.
     *
     * @return String representation of task in file format.
     */
    public String convertToFileFormat() {
        return String.format("%d / %s", isDone ? 1 : 0, description);
    }
}
