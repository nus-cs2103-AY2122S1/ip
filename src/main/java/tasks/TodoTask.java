package tasks;

import bot.TaskType;

/**
 * To-do task
 */
public class TodoTask extends Task {

    private String taskDesc;
    private final TaskType TASK_TYPE = TaskType.Todo;

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
        return this.TASK_TYPE;
    }

}
