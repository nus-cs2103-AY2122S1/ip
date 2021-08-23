package duke;

public class Task {
    protected final String taskDescription;
    protected boolean isDone;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    /**
     * Indicate that this task has been completed
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public String toDukeStoreFormat() {
        return String.format("%s | %s", this.isDone ? "1" : "0", this.taskDescription);
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", isDone ? 'X' : ' ', taskDescription);
    }
}
