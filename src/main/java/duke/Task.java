package duke;
public class Task {
    private final String description;
    private boolean isDone;
    protected char type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = ' ';
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + (this.isDone ? "[x] " : "[ ] ") + this.description;
    }

    public boolean markDone() {
        if (this.isDone) {
            return false;
        } else {
            this.isDone = true;
            return true;
        }
    }

}
