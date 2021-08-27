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

    /**
     * Returns DONE_STATUS_INDICATOR if task is done, and " " otherwise.
     *
     * @return DONE_STATUS_INDICATOR if task is done.
     */
    public String getStatusIcon() {
        return isDone ? DONE_STATUS_INDICATOR : " ";
    }

    /**
     * Returns a Task that is marked as done.
     *
     * @return a Task that is marked as done.
     */
    public abstract Task markAsDone();

    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), name);
    }
}
