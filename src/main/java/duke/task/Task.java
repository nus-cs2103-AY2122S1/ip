package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;

    public Task(String description, String taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public boolean hasKeyword(String keyword) {
        return description.contains(keyword);
    }

    public String saveTaskFormat() {
        return String.format("|%s|%s", isDone? 1 : 0, description);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + description;
    }
}