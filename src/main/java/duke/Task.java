package duke;

public class Task {
    private final String taskName;
    private boolean isDone;

    /**
     * Constructor for Task class.
     * @param taskName Name of task.
     */
    public Task(String taskName) {
        this.taskName = taskName.trim();
        this.isDone = false;
    }

    /**
     * Constructor for Task class.
     * @param taskName Name of task.
     * @param isDone True implies task has been done.
     */
    public Task(String taskName, boolean isDone) {
        this.taskName = taskName.trim();
        this.isDone = isDone;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getFileString() {
        return String.format("%d | %s", isDone ? 1 : 0, this.taskName);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", this.taskName);
    }
}
