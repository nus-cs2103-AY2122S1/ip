public class Task {

    private String description;
    private boolean isDone;

    public Task(String description) {
        this.isDone = false;
        this.description = description;
    }

    public Task(String description, boolean done) {
        this.isDone = done;
        this.description = description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    private String getDoneSymbol() {
        return isDone ? "X" : " ";
    }

    private String getDoneString() { return isDone ? "1" : "0"; }

    public String toFileData() { return String.format("%s,%s", getDoneString(), description); }

    @Override
    public String toString() {
        return String.format("[%s] %s", getDoneSymbol(), description);
    }
}
