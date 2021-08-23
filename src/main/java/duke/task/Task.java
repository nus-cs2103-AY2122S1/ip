package duke.task;

import java.time.format.DateTimeFormatter;

public abstract class Task {
    // formatting patterns for Tasks with date (and time) inputs
    protected final DateTimeFormatter dateTimePattern = DateTimeFormatter.ofPattern("MMM d yyyy hh:mma");
    protected final DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("MMM d yyyy");
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    protected boolean getStatus() {
        return this.isDone;
    }

    @Override
    public String toString() {
        String result = this.isDone ? "[X] " : "[ ] ";
        result += this.description;
        return result;
    }

    abstract public String databaseString();
}
