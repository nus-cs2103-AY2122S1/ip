package Duke.Task;

public class Todo extends Task {
    private static final String TASK_TYPE_ICON = "T";

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    protected String getTaskTypeIcon() {
        return TASK_TYPE_ICON;
    }
}
