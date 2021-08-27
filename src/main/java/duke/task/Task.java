package duke.task;

public class Task {
    private String taskName;
    private boolean status;

    Task(String taskName, boolean status) {
        this.taskName = taskName;
        this.status = status;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getStatus() {
        return status ? "1" : "0";
    }

    public String displayTask() {
        String display;
        if (status) {
            display = "|1|" + taskName;
        } else {
            display = "|0|" + taskName;
        }
        return display;
    }

    public void checkOffTask() {
        status = true;
    }
}
