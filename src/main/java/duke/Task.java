package duke;

/**
 * Represents a task. Contains the description and state of the task.
 * Allows for changing of the state of the class.
 */
public class Task {
    /** Description of the task */
    private String description;
    /** Indicates if task is done */
    private boolean isDone;
    /** The tag for the task */
    private String tag;

    /**
     * Constructor for Task.
     * Takes in the description of the task,
     * and sets the task as not done.
     *
     * @param description The description of the class.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tag = " ";
    }

    public String getDescription() {
        return description;
    }

    public String getTag() {
        return tag;
    }

    /**
     * Changes the status of the Task to true.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the string representation of the Task.
     *
     * @return String containing description and status indicator for Task.
     */
    @Override
    public String toString() {
        String tagString = tag.equals(" ") ? "" : " #" + tag;
        return "[" + getStatusIcon() + "] " + description + tagString;
    }

    /**
     * Returns the string representation of the task's state.
     *
     * @return "X" if task is done, " " otherwise.
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Adds tag to the task.
     *
     * @param tag The tag to be added.
     */
    public void addTag(String tag) {
        this.tag = tag;
    }

    /**
     * Returns a string formatted for writing into file.
     *
     * @return String representation of the task for file writing.
     */
    public String saveString() {
        return isDone + "," + description + "," + tag;
    }

}
