public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void finishTask() {
        isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String save() {
        return String.format("%s | %s", isDone, description);
    }
    
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + description;
    }

}
