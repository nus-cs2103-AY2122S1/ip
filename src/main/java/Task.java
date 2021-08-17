public class Task {
    private String taskName;
    private boolean status;

    Task(String taskName) {
        this.taskName = taskName;
        status = false;
    }

    public String displayTask() {
        String display;
        if (status) {
            display = "[X] " + taskName;
        } else {
            display = "[ ] " + taskName;
        }
        return display;
    }

    public void checkOffTask() {
        status = true;
    }
}
