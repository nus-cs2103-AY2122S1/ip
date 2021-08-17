public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    // getter
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    // setter
    public void markAsDone() {
        isDone = true;
    }

    public String toString() {
        return this.name;
    }
}
