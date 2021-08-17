public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        String s = "[" + getStatusIcon() + "] " + this.description;
        return s;
    }

    public String finished() {
        this.isDone = true;
        return this.toString();
    }
}
