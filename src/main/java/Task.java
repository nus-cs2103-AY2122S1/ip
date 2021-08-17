public class Task {

    private String description;
    private boolean isDone;

    public Task(String description) {
        this.isDone = false;
        this.description = description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    private String getDoneSymbol() {
        return isDone ? "X" : " ";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getDoneSymbol(), description);
    }
}
