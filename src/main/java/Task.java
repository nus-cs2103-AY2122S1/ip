public class Task {
    protected String description;
    protected boolean isDone;

    public Task (String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public void markedDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (this.isDone ? "[X]" : "[ ]");
    }

    public String printTask() {
        return this.getStatusIcon() + " " + this.getDescription();
    }
}
