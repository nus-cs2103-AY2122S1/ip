package model.task;

public class Task {
    private final String description;
    private boolean isDone;

    public Task(String entry) {
        this.description = entry;
    }

    public void markDone() {
        isDone = true;
    }

    public void markIncomplete() {
        isDone = false;
    }

    private String getStatus() {
        return isDone ? "X" : " ";
    }

    public String toString() {
        return "[" + this.getStatus() + "] " + this.description;
    }
}