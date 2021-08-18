public class Task {
    private String description;
    private boolean isDone;
    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return description + " " + getStatusIcon();
    }

    private String getStatusIcon() {
        return isDone ? "[\u2713]" : "";
    }
}
