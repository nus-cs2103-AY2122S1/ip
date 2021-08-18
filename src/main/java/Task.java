public class Task {
    private String taskname;
    private boolean completed;

    public Task(String taskname) {
        this.taskname = taskname;
        this.completed = false;
    }

    public void markAsCompleted() {
        this.completed = true;
    }

    @Override
    public String toString() {
        return this.taskname + " " + (this.completed ? "[X]" : "[ ]");
    }
}
