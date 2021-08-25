package tasks;

import bot.TaskType;

/**
 * General task
 */
public class GeneralTask extends Task {

    private String description;
    private final TaskType TASK_TYPE = TaskType.Blank;

    public GeneralTask(String description) {
        this.description = description;
    }

    @Override
    public String getTaskDesc() {
        return description;
    }

    @Override
    String getTaskText() {
        return this.description;
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
