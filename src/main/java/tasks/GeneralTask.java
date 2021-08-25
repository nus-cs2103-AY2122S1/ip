package tasks;

import bot.TaskType;

public class GeneralTask extends Task {

    private final String description;
    private final TaskType taskType = TaskType.Blank;

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
        return this.taskType;
    }

}
