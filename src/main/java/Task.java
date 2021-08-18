public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String done = this.isDone ? "X" : " ";
        return "[" + done + "] " + this.description;
    }
}
