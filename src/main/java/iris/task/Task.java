package iris.task;

/**
 * Encapsulates the representation of a task.
 * Contains a description of the task,
 * boolean representation of completion of task
 * and the type of task.
 *
 * @author Cheong Yee Ming
 * @version Iris Level-10
 */
public abstract class Task {

    public final TaskType taskType;
    protected final String description;
    protected boolean isDone;

    /**
     * Constructor for a task.
     *
     * @param description Description of task to be done.
     * @param isDone      Boolean representation of completion of task.
     * @param taskType    Type of the task.
     */
    protected Task(String description, boolean isDone, TaskType taskType) {
        assert !description.isBlank() : "Description of task cannot be blank.";
        this.description = description;
        this.isDone = isDone;
        this.taskType = taskType;
    }

    /**
     * Returns the status icon relative to state of completion.
     *
     * @return Status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a string representation of status of task.
     *
     * @return String representation of task status.
     */
    public String getStatus() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Marks a task as done.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks a task as undone.
     */
    public void markUndone() {
        isDone = false;
    }

    /**
     * Returns a string representation of task
     * used when saving task to local directory.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        return (getStatusIcon().equals("X") ? "1" : "0") + " | " + description;
    }

    /**
     * Returns a boolean representation whether the
     * description contains the keyword.
     *
     * @param keyword The keyword to look for in the description.
     * @return Boolean representation whether description contains keyword.
     */
    public boolean containsKeyword(String keyword) {
        return description.contains(keyword);
    }
}
