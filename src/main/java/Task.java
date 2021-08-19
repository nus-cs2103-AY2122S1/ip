public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String description() {
        return description;
    }

    public boolean status() {
        return isDone;
    }

    public void markDone() {
        this.isDone = true;
    }
}
