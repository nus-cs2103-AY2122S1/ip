public class ToDo {
    protected String description;
    protected boolean isDone;

    public ToDo(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public void setDone() {
        this.isDone = true;
    }
}
