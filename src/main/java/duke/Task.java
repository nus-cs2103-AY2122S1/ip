package duke;

/**
 * Encapsulates a task, which can be entered into the to-do-list.
 */
public class Task {
    private String description;
    private boolean isDone;
    private String tag = null;

    /**
     * Constructor for a task.
     *
     * @param description A short description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for a task.
     *
     * @param description A short description of the task.
     * @param isDone A boolean to indicate whether the task is already done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Constructor for a task.
     *
     * @param description A short description of the task.
     * @param isDone A boolean to indicate whether the task is already done.
     * @param tag The tag to attach to the task.
     */
    public Task(String description, boolean isDone, String tag) {
        this.description = description;
        this.isDone = isDone;
        this.tag = tag;
    }

    /**
     * Returns a character indicating whether the task is done or not.
     *
     * @return Icon 'X' if the task is done, ' ' whitespace otherwise.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Setter to mark the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the string representation of the task, in a format suitable for storing in a text file.
     *
     * @return The string representation of the task, in a format suitable for storing in a text file.
     */
    public String toStringForFile() {
        String status = (this.isDone) ? "1" : "0";
        return status + " | " + this.description + " | " + this.tag;
    }

    @Override
    public String toString() {
        if (this.tag == null || this.tag.equals("null")) {
            // There is no tag
            return "[" + this.getStatusIcon() + "] " + this.description;
        } else {
            // There is a tag
            return "[" + this.getStatusIcon() + "] " + this.description + " #" + this.tag;
        }
    }

    /**
     * Tag the task.
     *
     * @param tag The tag to attach to the task.
     */
    public void setTag(String tag) {
        this.tag = tag;
    }
}
