public class Task {
    private String taskName;
    private boolean state = false;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean getState() {
        return this.state;
    }

    public String getTaskName() {
        return this.taskName;
    }
}
