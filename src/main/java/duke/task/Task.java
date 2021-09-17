package duke.task;

import java.time.format.DateTimeFormatter;

public abstract class Task {
    private static final String DONE_ICON = "X";
    private static final String NOT_DONE_ICON = " ";
    private static final String DESCRIPTION_EMPTY_MESSAGE = "Description cannot be empty.";
    protected static final DateTimeFormatter DATE_DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

    private final String description;
    private boolean isDone;

    Task(String description, boolean isDone) throws InvalidTaskException {
        if (description.isEmpty()) {
            throw new InvalidTaskException(DESCRIPTION_EMPTY_MESSAGE);
        }
        this.description = description;
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public abstract String getTaskTypeIcon();

    @Override
    public String toString() {
        String taskStatusIcon = this.isDone ? DONE_ICON : NOT_DONE_ICON;
        return String.format("[%s] [%s] %s", this.getTaskTypeIcon(), taskStatusIcon, this.description);
    }
}
