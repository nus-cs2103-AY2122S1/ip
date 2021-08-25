package duke.task;


public class Task {
    private boolean isDone;
    private String detail;
    private String type;

    public Task(String detail, String type) {
        this.detail = detail;
        this.isDone = false;
        this.type = type;
    }

    public void markDone() {
        this.isDone = true;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getType() {
        return type;
    }

    public String getDetail() {
        return detail;
    }

    @Override
    public String toString() {
        String box = isDone ? "[X] " : "[ ] ";
        return box + this.detail;
    }
}
