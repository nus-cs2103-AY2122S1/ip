public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = "t";
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getType() {
        String s = this.type;
        String represent;
        switch (s) {
            case "t": represent = "T";
                break;
            case "d": represent = "D";
                break;
            case "e": represent = "E";
                break;
            default: represent = " ";
                break;
        }
        return represent;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getType() + "]" + "[" + this.getStatusIcon() + "] " + this.description;
    }
}
