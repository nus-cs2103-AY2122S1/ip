package duke.task;

/**
 * The Task class encapsulates a duke.task.
 */
abstract public class Task {
    protected String description;
    protected boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the status of the duke.task.
     *
     * @return "X" for done or " " for not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done duke.task with X
    }

    /**
     * Marks the duke.task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the duke.task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns duke.task string to be written into file.
     * Checks if the description contains input String.
     *
     * @param string Input String.
     * @return true if description contains input String, else false.
     */
    public boolean checkDescriptionContains(String string) {
        return description.contains(string);
    }

    /**
     * Gets duke.task string to be inputted into file.
     *
     * @return string in the respective duke.task format.
     */
    abstract public String getTaskFileString(String delimiter, String done, String notDone);

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
