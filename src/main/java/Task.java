public class Task {
    protected String description;
    protected boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String markDoneIcon() {
        return this.isDone ? "[X]" : "[ ]";
    }

    @Override
    public String toString() {
        return this.markDoneIcon() + " " + this.description;
    }
}
