package duke.tasklist;

public class Task {
    private final String name;
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

    public String getName() {
        return name;
    }

    public String save() {
        int done = isDone ? 1 : 0;
        return String.format("%d | %s", done, name);
    }
}
