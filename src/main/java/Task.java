public class Task {
    private String taskName;
    private boolean doneStatus;
    
    public Task(String taskName, boolean doneStatus) {
        this.taskName = taskName;
        this.doneStatus = doneStatus;
    }

    protected void setDone() {
        this.doneStatus = true;
    }

    @Override
    public String toString() {
        return this.doneStatus ? "[X] " + this.taskName : "[] " + this.taskName;
    }
}
