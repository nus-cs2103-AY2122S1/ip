package duke.task;

/**
 * Deadline, Event, and ToDo inherit from Task.
 * Represents an object in the TaskList.
 */
public abstract class Task {
    private String text;
    private boolean isDone;

    /**
     * Represents the constructor for the Task object.
     *
     * @param text Description of the Task.
     * @param isDone Whether the Task is done.
     */
    public Task(String text, boolean isDone) {
        this.text = text;
        this.isDone = isDone;
    }

    /**
     * Marks the Task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Gets the done status of the task in a string. Used for the console.
     *
     * @return The string representation of the done status.
     */
    private String getDoneStatus() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Gets the done status of the task as an integer. Used for taskList.txt.
     *
     * @return The integer representation of the done status.
     */
    public int getDoneInt() {
        return this.isDone ? 1 : 0;
    }

    /**
     * Gets the description of the Task.
     *
     * @return Description of the Task.
     */
    public String getText() {
        return this.text;
    }

    /**
     * Gets the save format of the Task used for taskList.txt.
     *
     * @return The String representation of the Task used for taskList.txt.
     */
    abstract String getSaveFormat();

    /**
     * Gets the String representation of the Task used in the console.
     *
     * @return The String representation of the Task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getDoneStatus(), getText());
    }
}
