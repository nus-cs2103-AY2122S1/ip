public abstract class Task {
    protected final String description;
    protected boolean isDone;
    protected final TaskType taskType;

    protected Task(String description, boolean isDone, TaskType taskType) {
        this.description = description;
        this.isDone = isDone;
        this.taskType = taskType;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getStatus(){
        return "[" + getStatusIcon() + "] " + description;
    }

    public void markDone(){
        isDone = true;
    }

    @Override
    public String toString(){
        return (getStatusIcon().equals("X") ? "1" : "0") + " | " + description;
    }
}