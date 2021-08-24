package biscuit.tasks;

/**
 * biscuit.tasks.Task class representing a pending task a User has
 */
public class Task {
    protected boolean isDone = false;
    protected String description;
    private final TaskType taskType;
    public Task(String description, TaskType taskType) {
        this.description = description;
        this.taskType = taskType;
    }

    public Task(boolean isDone, String description, TaskType taskType) {
        this.isDone = isDone;
        this.description = description;
        this.taskType = taskType;
    }

    /**
     * Get checkbox based on status
     *
     * @return checkbox either empty or crossed
     */
    protected String getCheckBox() {
        return isDone ? "[X] " : "[ ] ";
    }

    /**
     * Get box containing type of biscuit.tasks.Task abbreviated by a letter
     *
     * @return box containing task of Type
     */
    protected String getTypeBox() {
        String taskType;
        switch (this.taskType) {
        case DEADLINE:
            taskType = "D";
            break;
        case EVENT:
            taskType = "E";
            break;
        default:
            taskType = "T";
        }
        return "[" + taskType + "]";
    }

    public TaskType getType() {
        return taskType;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return getTypeBox() + getCheckBox() + description;
    }

    /**
     * Types of tasks available
     */
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }
}
