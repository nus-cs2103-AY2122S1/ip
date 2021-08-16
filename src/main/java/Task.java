public class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatus(), name);
    }

    public String getStatus() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDone() {
        isDone = true;
    }
}
