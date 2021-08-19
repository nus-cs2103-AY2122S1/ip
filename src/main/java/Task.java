public class Task {
    private final String taskContent;
    private boolean completed = false;
    public Task(String taskContent) {
        this.taskContent = taskContent;
    }
    public void markCompleted() {
        this.completed = true;
    }
    public String getTaskContent() {
        return this.taskContent;
    }
    public boolean isCompleted() {
        return this.completed;
    }
}
