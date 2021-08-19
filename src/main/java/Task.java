public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;
    protected String time;

    public Task(String description, String type, String time) {
        this.description = description;
        this.isDone = false;
        this.type = type;
        this.time = time;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getType() {
        return this.type;
    }

    public String getTime() {
        return this.time;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        if (this.type.equals("T")) {
            return "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.description;
        } else if (this.type.equals("D")) {
            return "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.description + " (by: " + this.time + ")";
        } else if (this.type.equals("E")) {
            return "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.description + " (at: " + this.time + ")";
        } else {
            return null;
        }
    }
}
