public class Task {

    private final String description;
    private boolean isDone;

    public Task(String task) {
        this.description = task;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    public boolean getDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return getDone() ? "[X]" : "[ ]";
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }
}
