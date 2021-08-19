public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getName() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setName(String desc) {
        this.description = desc;
    }

    public void setDone(boolean val) {
        this.isDone = val;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
