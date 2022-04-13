package duke.task;

/**
 * Encapsulates a task object.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for this task.
     *
     * @param description Details of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks whether this task contains the keyword.
     *
     * @param keyword Word to match in task list.
     * @return Boolean value whether the task contains keyword.
     */
    public boolean hasKeyword(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Returns task as string in the format to be saved.
     *
     * @return String representation of task to be saved.
     */
    public String saveTaskFormat() {
        return String.format("|%s|%s", isDone? 1 : 0, description);
    }

    /**
     * Returns the status icon of task.
     *
     * @return String representation of status icon of task.
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
     * @return String representation of task.
     */
    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + description;
    }
}