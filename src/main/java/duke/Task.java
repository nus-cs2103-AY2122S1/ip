package duke;

public class Task {
    private final String taskContent;
    private final String type;
    private boolean isCompleted = false;
    public Task(String taskContent, String type) {
        this.taskContent = taskContent;
        this.type = type;
    }
    public void markCompleted() {
        isCompleted = true;
    }
    public void markIncomplete() {
        isCompleted = false;
    }
    public String getTaskContent() {
        return taskContent;
    }
    public boolean isCompleted() {
        return isCompleted;
    }
    public String getType() {
        return type;
    }
    public String getTiming() {
        return "";
    }
}
