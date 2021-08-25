package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public Task(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getIsCompleted() {
        return this.isCompleted;
    }

    public void markCompleted() {
        this.isCompleted = true;
    }

    @Override
    public String toString() {
        String statusIcon = this.isCompleted ? "X" : " ";
        return "[" + statusIcon + "] " + this.description;
    }
}
