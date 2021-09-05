package duke.task;

import java.time.LocalDate;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return description;
    }

    public boolean getDone() {
        return isDone;
    }

    public LocalDate getDate() {
        return null;
    }

    public String toFileFormat() {
        return "";
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
