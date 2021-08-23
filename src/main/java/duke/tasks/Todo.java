package duke.tasks;

public class Todo extends Task {
    private static final String TASK_TAG = "todo";

    public Todo(String taskName) {
        super(taskName);
    }

    public Todo(String taskName, boolean isDone) {
        super(taskName, isDone);
    }

    public String fileSaveFormat() {
        return String.format("T | %d | %s", this.isDone() ? 1 : 0, this.taskName());
    }

    public static String taskTag() {
        return TASK_TAG;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
