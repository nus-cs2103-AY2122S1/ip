package duke.task;

public abstract class Task {
    private boolean isDone;
    private String taskDetails;

    public Task() {
        this.isDone = false;
        this.taskDetails = "";
    }

    public Task(String taskDetails) {
        this.isDone = false;
        this.taskDetails = taskDetails;
    }

    public void markDone() {
        isDone = true;
    }

    public String getTaskDetails() {
        return taskDetails;
    }

    public int isDoneToInt() {
        return isDone ? 0 : 1;
    }

    public abstract String taskType();

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + taskDetails;
        } else {
            return "[ ] " + taskDetails;
        }
    }
}
