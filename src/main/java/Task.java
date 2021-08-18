public class Task {
    private String description, type;
    private boolean isDone;

    public Task(String description, String type) {
        this.description = description;
        this.type = type;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String done = this.isDone ? "X" : " ";
        return "[" + type + "][" + done + "] " + this.description;
    }
}
