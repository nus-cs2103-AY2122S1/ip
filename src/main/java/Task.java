public abstract class Task {

    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public String getDescriptionWithStatus() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

}
