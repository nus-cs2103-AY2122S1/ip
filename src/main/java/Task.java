public class Task {
    private String taskName;
    private boolean isDone = false;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public String showTask() {
        return taskName;
    }

    public String checkDone() {
        return isDone ? "[X]" : "[ ]";
    }

    public void isDone() {
        isDone = true;
    }
}
