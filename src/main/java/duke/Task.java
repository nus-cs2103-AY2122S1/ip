package duke;

public class Task {
    private final String taskContent;
    private final String type;
    private boolean completed = false;
    public Task(String taskContent, String type) {
        this.taskContent = taskContent;
        this.type = type;
    }
    public void markCompleted() {
        completed = true;
    }
    public void markIncomplete() {
        completed = false;
    }
    public String getTaskContent() {
        return taskContent;
    }
    public boolean isCompleted() {
        return completed;
    }
    public String getType() {
        return type;
    }
    public String getTiming() {
        return "";
    }
}
