package tasks;

import bot.TaskType;

public class TodoTask extends Task {

    private String taskDesc;
    private final TaskType TASK_TYPE = TaskType.Todo;

    public TodoTask(String description) {
        this.taskDesc = description.trim();
    }

    @Override
    String getTaskDesc() {
        return this.taskDesc;
    }

    @Override
    String getTaskText() {
        return this.taskDesc;
    }

    @Override
    String getTaskTime() {
        return "";
    }

    @Override
    TaskType getTaskType() {
        return this.TASK_TYPE;
    }

}
