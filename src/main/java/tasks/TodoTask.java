package tasks;

import bot.TaskType;

/**
 * To-do task
 */
public class TodoTask extends Task {

    private final String taskDesc;
    private final TaskType taskType = TaskType.Todo;

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
        return this.taskType;
    }

}
