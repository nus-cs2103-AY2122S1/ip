public class Task {
    private String name;
    private boolean isDone;
    private int position;

    public Task(String name, int pos) {
        this.name = name;
        this.isDone = false;
        this.position = pos;
    }

    public void mark() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getName() {
        return this.name;
    }
}
