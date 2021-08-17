public class Task {

    private boolean completed;
    public String taskName;
    public String status;

    public Task(String name) {
        this.completed = false;
        this.taskName = name;
        this.status = "[ ] " + name;
    }

    public void complete() {
        this.completed = true;
        status = "[X] " + taskName;
    }

}
