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

    public String getDescription() {
        return this.description;
    }

    protected boolean getStatus() {
        return this.isDone;
    }

    @Override
    public String toString() {
        String result = this.isDone ? "[X] " : "[ ] ";
        result += this.description;
        return result;
    }
}
