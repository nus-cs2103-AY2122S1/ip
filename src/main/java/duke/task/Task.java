package duke.task;

/**
 * Encapsulates a task object.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;

    /**
     * Constructor for this task.
     *
     * @param description Details of the task
     * @param taskType Type of task (event, todo, deadline)
     */
    public Task(String description, String taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Returns task as string in the format to be saved.
     *
     * @return String representation of task to be saved
     */
    public String saveTaskFormat() {
        return String.format("|%s|%s", isDone? 1 : 0, description);
    }

    /**
     * Returns the status icon of task.
     *
     * @return String representation of status icon of task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Sets status of task to true.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Returns task status and description.
     *
     * @return String representation of task
     */
    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + description;
    }
}