public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String d) {
        this.description = d;
        this.isDone = false;
    }

    public void setDone() {
        isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String toString() {
        return description;
    }
}
