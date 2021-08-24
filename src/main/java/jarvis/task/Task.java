package jarvis.task;

public class Task {
    private String description;
    private boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }

    public String toStorageFormatString() {
        return String.format("%s;;;%s", isDone ? "1" : "0", description);
    }
}
