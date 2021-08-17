public class Task {
    public enum Type {
        TODO,
        DEADLINE,
        EVENT
    }
    protected boolean isDone = false;
    protected String description;
    public Type type;

    public Task(String description, Type type) {
        this.description = description;
        this.type = type;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    protected String getCheckBox() {
        return isDone ? "[X] " : "[ ] ";
    }

    protected String getTypeBox() {
        String taskType = "T";
        switch (this.type) {
            case DEADLINE:
                taskType = "D";
                break;
            case EVENT:
                taskType = "E";
                break;
        }
        return "[" + taskType + "]";
    }

    @Override
    public String toString() {
        return getTypeBox() + getCheckBox() + description;
    }
}
