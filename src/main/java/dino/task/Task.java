package dino.task;


/**
 * Represents a generic Task which all the actual tasks inherit from.
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;
    protected String taskType;
    protected String dueDate;

    /**
     * Constructor for the task object
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the String representation of the task status
     *
     * @return "X" if the task is done, " " otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public int getDoneStatus() {
        return this.isDone ? 1 : 0;
    }

    /**
     * Toggles the isDone boolean to be true
     */

    public void setIsDone() {
        this.isDone = true;
    }

    /**
     * Returns the description of the task
     *
     * @return string representing the description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the task type
     *
     * @return "T", "D" or "E"
     */
    public String getTaskType() {
        return this.taskType;
    }

    /**
     * Returns the dueDate of the task
     *
     * @return String representing the dueDate of task
     */
    public String getDueDate() {
        return this.dueDate;
    }

    public boolean hasKeyword(String keyword) {
        return this.description.contains(keyword);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
