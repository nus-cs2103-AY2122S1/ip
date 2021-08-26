public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean completed) {
        this.description = description;
        this.isDone = completed;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }
    public void markIsDone() {
        this.isDone = true;
    }

    public boolean checkDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);

    }

    public abstract String getType();
    public abstract String getDeadline();
}
