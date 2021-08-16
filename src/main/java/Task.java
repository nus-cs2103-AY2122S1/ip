public abstract class Task {
    private final String name;
    private final boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public String getName() {
        return name;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    abstract Task markAsDone();

    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), name);
    }
}
