package duke.task;

public abstract class Task {

    private final String taskName;
    private final boolean isCompleted;
    protected static String dateTimeFormat = "E, dd MMM yyyy, HH:mm"; 

    Task(String itemName) {
        this.taskName = itemName;
        this.isCompleted = false;
    }

    protected Task(Task oldTask) {
        this.taskName = oldTask.taskName;
        this.isCompleted = true;
    }

    public abstract Task markAsCompleted();

    public String getTaskName() {
        return this.taskName;
    }

    public boolean getIsCompleted() {
        return this.isCompleted;
    }

    @Override
    public String toString() {
        return "[" + (isCompleted ? "X": " ") + "] " + this.taskName;
    }
}
