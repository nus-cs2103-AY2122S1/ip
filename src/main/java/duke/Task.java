package duke;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;

    }

    Task(String description) {
        this(description, false);

    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[" + getStatusIcon() + "]" + " " + description);
        return sb.toString();
    }

    public void markAsDone() {
        this.isDone = true;
    }
}