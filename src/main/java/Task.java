public class Task {
    private String action;
    private boolean isCompleted;

    public Task(String action) {
        this.action = action;
        this.isCompleted = false;
    }

    public void complete() {
        this.isCompleted = true;
    }

    private String getStatusIcon() {
        return isCompleted ? "X" : " ";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.action);
    }
}
