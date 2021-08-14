package lifeline.task;

public class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return isDone;
    }

    public String toString() {
        if (isDone) {
            return "[✓] " + this.name;
        } else {
            return "[✗] " + this.name;
        }
    }
}
