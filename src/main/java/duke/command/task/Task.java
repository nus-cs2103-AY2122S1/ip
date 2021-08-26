package duke.command.task;

/**
 * Deadline, Event, and ToDo inherit from Task.
 * Represents an object in the TaskList
 */
public abstract class Task {
    private final String text;
    private boolean isDone;

    /**
     * Constructor for the Task object
     * @param text Description of the Task
     * @param isDone Whether the Task is done
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
     * @return The string representation of the done status
     */
    private String getDoneStatus() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Gets the done status of the task as an integer. Used for taskList.txt
     * @return The integer representation of the done status
     */
    public int getDoneInt() {
        return this.isDone ? 1 : 0;
    }

    /**
     * Gets the description of the Task.
     * @return
     */
    public String getText() {
        return this.text;
    }

    /**
     * The save format of the Task used for taskList.txt
     * @return The String representation of the Task used for taskList.txt
     */
    abstract String getSaveFormat();

    /**
     * The String representation of the Task used in the console.
     * @return The String representation of the Task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getDoneStatus(), getText());
    }
}