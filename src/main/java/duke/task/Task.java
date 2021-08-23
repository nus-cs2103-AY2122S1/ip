package duke.task;

public abstract class Task {
    private String taskName;
    private boolean isDone = false;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void setDone() {
        isDone = true;
    }

    public String getTaskName() {
        return taskName;
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
        return completedBox + " " + taskName;
    }
}