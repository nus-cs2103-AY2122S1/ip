public class Task {

    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String statusIcon = this.isDone ? "X" : " ";
        return "[" + statusIcon + "] " + this.description;
    }
}