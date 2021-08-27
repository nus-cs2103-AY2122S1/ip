package duke.task;

public class Task {
    private String taskName;
    private boolean isDone;

    Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    public String getStatus() {
        return isDone ? "1" : "0";
    }

    public String displayTask() {
        String display;
        if (isDone) {
            display = "|1|" + taskName;
        } else {
            display = "|0|" + taskName;
        }
        return display;
    }

    public void checkOffTask() {
        isDone = true;
    }
}
