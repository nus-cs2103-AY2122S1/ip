public class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return (this.isDone ? "[x] " : "[ ] ") + this.description;
    }

    public boolean markDone() {
        if (this.isDone == true) {
            return false;
        } else {
            this.isDone = true;
            return true;
        }
    }




}
