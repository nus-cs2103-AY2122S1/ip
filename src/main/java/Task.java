public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (this.isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    public Task markDone() {
        this.isDone = true;
        return this;

    }

    public String formatChange() {
        String mark = isDone ? "1" : "0";
        return "|" + mark + "0" + this.description;

    }

    @Override
    public String toString() {
        return this.getStatusIcon()  + this.description;
    }
}
