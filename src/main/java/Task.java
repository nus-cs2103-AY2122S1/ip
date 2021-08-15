public class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Task markAsDone() {
        isDone = true;
        return this;
    }

    private String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public String getTaskName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getTaskName());
    }
}
