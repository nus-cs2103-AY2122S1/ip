package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean isKeyword(String keyword) {
        if (this.description.contains(keyword)) {
            return true;
        } else {
            return false;
        }
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void Done() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
