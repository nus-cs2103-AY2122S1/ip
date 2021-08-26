package duke;

public class Task {
    private boolean isDone;
    private String description;

    public Task() {
        this.description = null;
        this.isDone = false;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getTaskIcon() {
        return "a";
    }

    public String getStatusIcon() {
        return (isDone ? "1" : "0");
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return this.description;
    }

}
