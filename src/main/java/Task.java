public class Task { //Base Task Class
    private final String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String done() {
        this.isDone = true;
        return this.toString();
    }

    public String write() {
        if (this.isDone) {
            return "| 1 | " + this.name;
        } else {
            return "| 0 | " + this.name;
        }
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }
}
