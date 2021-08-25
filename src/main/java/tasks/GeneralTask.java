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
    public String getTaskText() {
        return this.description;
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
