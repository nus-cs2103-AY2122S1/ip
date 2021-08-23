public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void setDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusAndDescription() {return this.getStatusIcon() + " " + this.getDescription(); }
}
