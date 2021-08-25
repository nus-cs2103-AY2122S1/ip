package duke.task;

public abstract class Task {
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

    public int isCompleted() {
        return completed ? 1 : 0;
    }

    public abstract String toStorageFormat();

//    @Override
//    public String toString() {
//        String statusIcon = completed ? "X" : " ";
//        return String.format("[%s] %s", statusIcon, taskName);
//    }

}
