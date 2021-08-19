public class Task {
    protected String description;
    protected boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public String markDoneIcon() {
        return this.isDone ? "[X]" : "[ ]";
    }

    @Override
    public String toString() {
        return this.markDoneIcon() + " " + this.description;
    }
}
