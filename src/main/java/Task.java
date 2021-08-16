public class Task {
    private enum Type {
        T, D, E
    }
    protected String description;
    protected boolean isDone;
    protected Type type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public void markedAsDone() {
        isDone = true;
    }
}