package duke.task;

import java.util.Locale;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String task, boolean isDone) {
        this.description = task;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return isDone ? "[X] " : "[ ] ";
    }
    public String check() {
        this.isDone = true;
        return this.toString();
    }

    public String toString() {
        return getStatusIcon() + description;
    }

    public String toSaveString() {
        return isDone ? "1" : "0" + "|" + this.description;
    }

    public boolean findKeyword(String keyword) {
        String processed = description;
        return processed.toLowerCase().contains(keyword);
    }
}
