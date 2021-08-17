abstract public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this(description, false);
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void toggle() {
        this.toggle(!this.isDone);
    }

    public void toggle(boolean isDone) {
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    abstract public String getTaskIcon();

    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }
}