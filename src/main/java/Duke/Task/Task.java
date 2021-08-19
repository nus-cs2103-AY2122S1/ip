package Duke.Task;

public abstract class Task {
    private static final String DONE_ICON = "X";
    private static final String NOT_DONE_ICON = " ";
    private static final String DESCRIPTION_EMPTY_MESSAGE = "Description cannot be empty.";

    private final String description;
    private boolean isDone;

    Task(String description) throws InvalidTaskException {
        if (description.isEmpty()) throw new InvalidTaskException(DESCRIPTION_EMPTY_MESSAGE);
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    protected abstract String getTaskTypeIcon();

    @Override
    public String toString() {
        String taskStatusIcon = this.isDone ? DONE_ICON : NOT_DONE_ICON;
        return String.format("[%s] [%s] %s", this.getTaskTypeIcon(), taskStatusIcon, this.description);
    }
}
