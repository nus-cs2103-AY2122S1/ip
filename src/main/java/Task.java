public class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + name;
    }

    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    public void setDone() {
        this.isDone = true;
    }
}
