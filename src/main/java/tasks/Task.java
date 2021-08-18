package tasks;

public class Task {
    private boolean completed;

    public Task() {
        completed = false;
    }
//    private String taskName;

//    public Task(String taskName) {
//        completed = false;
//        this.taskName = taskName;
//    }

    public void setCompleted() {
        completed = true;
    }

    public String getStatusIcon() {
        return completed ? "X" : " ";
    }

//    @Override
//    public String toString() {
//        String statusIcon = completed ? "X" : " ";
//        return String.format("[%s] %s", statusIcon, taskName);
//    }

}
