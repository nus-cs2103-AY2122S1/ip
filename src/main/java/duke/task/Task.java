package duke.task;

public class Task {
    private String name;
    private boolean completed;
    private String taskType = "G";

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    public Task(String name, String taskType) {
        this.name = name;
        this.completed = false;
        this.taskType = taskType;
    }

    public Task(String name, boolean completed, String taskType) {
        this.name = name;
        this.completed = completed;
        this.taskType = taskType;
    }

    public String getName() {
        return name;
    }

    public boolean completeTask() {
        this.completed = true;
        return true;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public String getTaskType() {
        return taskType;
    }

    public String getSaveFormat() {
        return String.format("%s|%s|%s", this.taskType, this.completed ? "c" : "i", this.name);
    }
}
