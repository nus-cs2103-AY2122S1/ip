package tasks;

public class Task {
    private boolean completed;
    private String taskName;

    public Task(String taskName) {
        completed = false;
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        String statusIcon = completed ? "X" : " ";
        return String.format("[%s] %s", statusIcon, taskName);
    }

}
