package duke.tasks;

public abstract class Task {
    protected final String description;
    protected boolean isDone;
    protected boolean isHighOrLow;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.isHighOrLow = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.isHighOrLow = false;
    }

    public Task(String description, boolean isDone, boolean isHighOrLow) {
        this.description = description;
        this.isDone = isDone;
        this.isHighOrLow = isHighOrLow;
    }


    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone() {
        isDone = true;
    }

    public boolean isHighOrLow() {
        return  isHighOrLow;
    }

    public void markAsHigh() {
        isHighOrLow = true;
    }

    public abstract String showTask();

    public abstract String saveTask();
}