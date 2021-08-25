package tasks;

import bot.TaskType;

public class TodoTask extends Task {

    private final String taskDesc;
    private final TaskType taskType = TaskType.Todo;

    public TodoTask(String description) {
        this.taskDesc = description.trim();
    }

    @Override
    public String getTaskDesc() {
        return this.taskDesc;
    }

    @Override
    public String getTaskText() {
        return this.taskDesc;
    }

    @Override
    public String getTaskTime() {
        return "";
    }

    @Override
    public TaskType getTaskType() {
        return this.taskType;
    }

}
