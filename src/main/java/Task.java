public class Task {
    private String desc;
    private boolean isDone;

    public Task(String desc) {
        this.desc = desc;
        isDone = false;
    }

    public String getDesc() {
        return desc;
    }

    public String statusIcon() {
        return isDone ? "[X] " : "[ ] ";
    }

    public void markAsDone() {
        this.isDone = true;
    }
}
