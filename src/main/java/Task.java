public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    public void markAsDone() {
        this.isDone = true;
    }


    private String getStatus() {
        return this.isDone ? "X" : " ";
    }


    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatus(), this.description);
    }
}
