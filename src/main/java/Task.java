public class Task {
    final protected String descriptions;
    final protected boolean isDone;

    public Task(String descriptions) {
        this.descriptions = descriptions;
        this.isDone = false;
    }

    public Task(String descriptions, boolean isDone) {
        this.descriptions = descriptions;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public Task done() {
        return new Task(this.descriptions, true);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.descriptions);
    }
}
