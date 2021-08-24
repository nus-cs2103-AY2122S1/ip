public abstract class Task {
    protected String description;
    protected boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public boolean isDone() { return this.isDone; }

    public String getFormattedData() {
        return this.getTaskIdentifier() + "|" + (this.isDone ? 1 : 0) + "|" + this.description;
    }

    public abstract String getTaskIdentifier();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

}
