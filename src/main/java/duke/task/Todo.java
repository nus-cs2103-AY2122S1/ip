package duke.task;

public class Todo extends Task {
    public static final String TASK_TYPE_ICON = "T";

    public Todo(String description, boolean isDone) throws InvalidTaskException {
        super(description, isDone);
    }

    public Todo(String description) throws InvalidTaskException {
        this(description, false);
    }

    @Override
    public String getTaskTypeIcon() {
        return TASK_TYPE_ICON;
    }
}
