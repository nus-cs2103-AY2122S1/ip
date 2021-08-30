package duke.task;

/**
 * Encapsulates the representation of a task.
 * Contains a description of the task,
 * boolean representation of completion of task
 * and the type of task.
 *
 * @author Cheong Yee Ming
 * @version Duke A-JavaDoc
 */
public abstract class Task {
    protected final String description;
    protected boolean isDone;
    public final TaskType taskType;

    /**
     * Constructor for a task.
     *
     * @param description Description of task to be done.
     * @param isDone      Boolean representation of completion of task.
     * @param taskType    Type of the task.
     */
    protected Task(String description, boolean isDone, TaskType taskType) {
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
    public String getStatus(){
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Marks a task as done.
     */
    public void markDone(){
        isDone = true;
    }

    /**
     * Returns a string representation of task
     * used when saving task to local directory.
     *
     * @return String representation of task.
     */
    @Override
    public String toString(){
        return (getStatusIcon().equals("X") ? "1" : "0") + " | " + description;
    }
}