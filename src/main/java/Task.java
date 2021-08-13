public class Task {
    private String name;
    private boolean isDone;

    Task(String name) {
        this.name = name;
        this.isDone = false;
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
