public class Task {
    protected String descriptions;
    protected boolean isDone;

    public Task(String descriptions) {
        this.descriptions = descriptions;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public Task done() {
        this.isDone = true;
        return this;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.descriptions);
    }
}
