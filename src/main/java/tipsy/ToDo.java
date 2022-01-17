package tipsy;

public class ToDo extends Task {
    public ToDo(String taskName, boolean isDone) {
        super(taskName, TaskType.TODO, isDone);
    }

    public ToDo(String taskName) {
        this(taskName, false);
    }

    @Override
    public String toSaveFormat() {
        return String.format("T, %d, %s", isDone() ? 1 : 0, getTaskName());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
