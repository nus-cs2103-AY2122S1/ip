package Duke.Task;

public class Todo extends Task {
    private static final String TASK_TYPE_ICON = "T";

    public Todo(String description) throws InvalidTaskException {
        super(description);
    }

    @Override
    protected String getTaskTypeIcon() {
        return TASK_TYPE_ICON;
    }
}
