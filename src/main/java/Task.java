public class Task {
    private final String taskContent;
    private boolean completed = false;
    public Task(String taskContent) {
        this.taskContent = taskContent;
    }
    public void markCompleted() {
        this.completed = true;
    }
    @Override
    public String toString() {
        if(this.completed) {
            return "[X] " + this.taskContent;
        }else {
            return "[ ] " + this.taskContent;
        }
    }
}
