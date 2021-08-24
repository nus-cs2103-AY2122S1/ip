public class Task {
    private boolean isDone;
    private String detail;
    private String type;
    private String time;

    public Task(String detail, String type) {
        this.detail = detail;
        this.isDone = false;
        this.type = type;
    }

    public Task(String detail, String type, String time) {
        this.detail = detail;
        this.isDone = false;
        this.type = type;
        this.time = time;
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

    public String getTime() { return time; }

    @Override
    public String toString() {
        String box = isDone ? "[X] " : "[ ] ";
        return box + this.detail;
    }
}
