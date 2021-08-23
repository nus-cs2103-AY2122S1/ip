/**
 * Task class representing a pending task a User has
 */
public class Task {
    /**
     * Types of tasks available
     */
    public enum Type {
        TODO,
        DEADLINE,
        EVENT
    }
    protected boolean isDone = false;
    protected String description;
    private Type type;

    public Task(String description, Type type) {
        this.description = description;
        this.type = type;
    }

    public Task(boolean isDone, String description, Type type) {
        this.isDone = isDone;
        this.description = description;
        this.type = type;
    }

    /**
     * Get checkbox based on status
     * @return checkbox either empty or crossed
     */
    protected String getCheckBox() {
        return isDone ? "[X] " : "[ ] ";
    }

    /**
     * Get box containing type of Task abbreviated by a letter
     * @return box containing task of Type
     */
    protected String getTypeBox() {
        String taskType;
        switch (this.type) {
        case DEADLINE:
            taskType = "D";
            break;
        case EVENT:
            taskType = "E";
            break;
        default:
            taskType= "T";
        }
        return "[" + taskType + "]";
    }

    public Type getType() {
        return type;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    @Override
    public String toString() {
        return getTypeBox() + getCheckBox() + description;
    }
}
