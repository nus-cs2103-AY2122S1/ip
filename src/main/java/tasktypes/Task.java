package tasktypes;

public class Task {
    private String description;
    private String taskType;
    private boolean isDone;

    // constructor for task object
    public Task(String description, String taskType) {
        this.description = description;
        this.taskType = taskType;
        this.isDone = false;
    }

    public void changeStatus(boolean result) {
        this.isDone = result;
    }

    public boolean getBooleanStatus() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String hardDiskSave() {
        return "";
    }

    @Override
    public String toString() {
        return ("[" + getStatus() + "] " + this.description);
    }
}
