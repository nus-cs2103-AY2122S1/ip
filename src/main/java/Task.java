public class Task {
    private String task;
    private boolean completed;

    public Task(String task) {
        this.task = task;
        this.completed = false;
    }

    public boolean getCompleted() {
        return this.completed;
    }

    public String getMarker() {
        return (this.completed? "X" : " ");
    }

    public boolean setCompleted() {
        this.completed = true;
        return this.completed;
    }

    public String getTask() {
        return this.task;
    }
}
