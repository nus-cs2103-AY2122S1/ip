package duke.task;
public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;
    protected String dueDate;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTaskType() {
        return this.taskType;
    }

    public String getDueDate() {
        return this.dueDate;
    }

    public int getDoneStatus() {
        return this.isDone ? 1 : 0;
    }

    public boolean hasKeyword(String keyword) {
        return this.description.contains(keyword);
    }


    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}