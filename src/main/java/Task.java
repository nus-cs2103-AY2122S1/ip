public abstract class Task {
    private TaskType type;
    private String description;
    private boolean isDone;

    public Task(TaskType type, String description) {
        this(type, description, false);
    }

    public Task(TaskType type, String description, boolean isDone) {
        this.type = type;
        this.description = description;
        this.isDone = isDone;
    }

    public TaskType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "[✔]" : "[ ]"); // mark done task with ✔
    }

    public void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
