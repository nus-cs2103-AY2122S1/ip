package duke.task;

public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return this.isDone ? "[X]" : "[ ]"; // Done task marked with X
    }

    public String getStatusNumber() {
        return this.isDone ? "1" : "0";
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public void changeDescription(String newDesc) {
        this.description = newDesc;
    }

    @Override
    public String toString() {
        return (this.getStatusIcon() + " " + this.getDescription());
    }

    public abstract String toStorageString();
}
