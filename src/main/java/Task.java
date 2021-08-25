public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone(){
        isDone = true;
    }

    public String taskIndicator() {
        return " ";
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String toString()
    {
        return "[" + getStatusIcon() +"] " + this.description;
    }

}