package biscuit.task;

/**
 * Task class representing a pending task a User has.
 */
public class Task {

    /** Types of tasks available. */
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    protected boolean isDone = false;
    protected String description;
    private final TaskType taskType;

    /**
     * Constructs Task class.
     *
     * @param description Task description.
     * @param taskType    Task type.
     */
    public Task(String description, TaskType taskType) {
        this.description = description;
        this.taskType = taskType;
    }

    /**
     * Constructs Task class.
     * Used when need to set isDone.
     *
     * @param isDone      Boolean of if task is done.
     * @param description Task description.
     * @param taskType    Task type.
     */
    public Task(boolean isDone, String description, TaskType taskType) {
        this.isDone = isDone;
        this.description = description;
        this.taskType = taskType;
    }

    /**
     * Gets checkbox based on status.
     *
     * @return Checkbox either empty or crossed.
     */
    protected String getCheckBox() {
        return isDone ? "[X] " : "[ ] ";
    }

    /**
     * Gets box containing type of biscuit.tasks.Task abbreviated by a letter.
     *
     * @return Box containing task of Type.
     */
    protected String getTypeBox() {
        String type;
        switch (taskType) {
        case DEADLINE:
            type = "D";
            break;
        case EVENT:
            type = "E";
            break;
        default:
            type = "T";
        }
        return "[" + type + "]";
    }

    /**
     * Gets Task type.
     *
     * @return Task type.
     */
    public TaskType getType() {
        return taskType;
    }

    /**
     * Gets boolean of if task is done.
     *
     * @return Boolean of is task is done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets boolean of if task is done.
     *
     * @param done Boolean of is task is done.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Gets task description.
     *
     * @return Task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns string representation of Task.
     *
     * @return Task String.
     */
    @Override
    public String toString() {
        return getTypeBox() + getCheckBox() + description;
    }

}
