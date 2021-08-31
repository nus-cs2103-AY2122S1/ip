package duke.tasks;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public boolean getCompletionStatus() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markTaskDone() {
        this.isDone = true;
    }

    public String saveText() {
        String isDone = this.isDone ? "1" : "0";
        return isDone + " / " + this.description;
    }

    @Override
    public String toString() {
        String taskStr = String.format("[%s] ", getStatusIcon())
                + this.description;
        return taskStr;
    }
}
