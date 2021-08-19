public class Task {
    private boolean isDone;
    private String detail;

    public Task(String detail) {
        this.detail = detail;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String box = isDone ? "[X] " : "[ ] ";
        return box + this.detail;
    }
}
