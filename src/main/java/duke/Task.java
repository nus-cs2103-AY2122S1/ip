package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(){
        this.description = "";
        this.isDone = false;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void done() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public int getStatus() {
        return isDone ? 1 : 0;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTaskTime() {
        return "";
    }

    public String getIcon() {
        return "";
    }
}
