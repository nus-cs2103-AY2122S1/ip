package duke.task;

public abstract class Task {
    private final String name;
    private final boolean isDone;
    public static final String DONE_STATUS_INDICATOR = "X";

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
        return isDone ? DONE_STATUS_INDICATOR : " ";
    }

    public abstract Task markAsDone();

    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), name);
    }
}
