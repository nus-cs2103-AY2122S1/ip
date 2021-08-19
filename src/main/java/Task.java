public class Task {

    private boolean completed;
    private String taskName;
    protected String status;

    public Task(String name) {
        this.completed = false;
        this.taskName = name;
        this.status = "[ ] " + name;
    }

    public void complete() {
        this.completed = true;
        this.status = this.status.replaceFirst(" ", "X");
    }

}
