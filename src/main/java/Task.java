public class Task {

    public enum Type {
        TODO, DEADLINE, EVENT
    }

    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public boolean getStatus() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : "") + "]"
                + description;
    }
}
