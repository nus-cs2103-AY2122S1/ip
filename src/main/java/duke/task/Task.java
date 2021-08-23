package duke.task;

public abstract class Task {
    private String taskDetails;
    private boolean isDone = false;

    public Task(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    public void setDone() {
        isDone = true;
    }

    public String getTaskName() {
        return taskDetails;
    }

    public boolean isDone() {
        return isDone;
    }

    public abstract String getTag();

    public abstract String getAdditionalInfo();

    @Override
    public String toString() {
        String completedBox;
        if(isDone) {
            completedBox="[X]";
        } else {
            completedBox="[ ]";
        }
        return completedBox + " " + taskDetails;
    }
}