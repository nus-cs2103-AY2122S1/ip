public class Task {

    private boolean completed;
    private String taskName;

    public Task(String taskname) {
        this.completed = false;
        this.taskName = taskname;
    }

    public void markAsCompleted() {
        this.completed = true;
    }

    public boolean checkIfCompleted() {
        return this.completed;
    }

    public String getTaskName() {
        return this.taskName;
    }
}
