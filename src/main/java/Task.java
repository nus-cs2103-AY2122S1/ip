public class Task {
    protected String description;
    protected String date;
    protected String time;
    protected boolean isDone;

    public Task(String description, String date, String time) {
        this.description = description;
        this.date = date;
        this.time = time;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getReadableString() {
        return "";
    }

    public String getFormattedDate() {
        return "";
    }

    public String getFormattedTime() {
        return "";
    }

    public String getDate() { return this.date; }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
