package duke.task;

/**
 * Tracks a task with a description and whether it has been completed yet.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor.
     *
     * @param description details about the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon to represent whether this task has been done or not.
     *
     * @return
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets this task to be done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the duke.Task's String.
     *
     * @return String form of duke.Task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Checks if word is contained in description.
     *
     * @param word to check.
     * @return boolean.
     */
    public boolean contains(String word) {
        return this.description.contains(word);
    }

    /**
     * Returns identifier of the task.
     * -1 rep invalid; 0 rep Todo; 1 rep Deadline; 2 rep Event.
     *
     * @return int.
     */
    public char getTaskType() {
        return '0';
    }

    /**
     * Converts the task into the format that will be saved into Hard drive.
     *
     * @return String.
     */
    public String toSavedFormat() {
        return "";
    }

    /**
     * Returns a boolean of whether the task is done or not.
     *
     * @return boolean.
     */
    public boolean isDone() {
        return this.isDone;
    }
}
