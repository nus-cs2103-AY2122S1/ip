package tasks;

import java.time.LocalDateTime;

import bot.TaskType;

/**
 * Deadline task
 */
public class DeadlineTask extends Task {

    private String taskText;
    private LocalDateTime taskTime;
    private final TaskType TASK_TYPE = TaskType.Deadline;

    public DeadlineTask(String taskText, LocalDateTime taskTime) {
        this.taskText = taskText.trim();
        this.taskTime = taskTime;
    }

    @Override
    public String getTaskDesc() {
        return String.format("%s (by: %s)", taskText, getTaskTime());
    }

    @Override
    public String getTaskText() {
        return this.taskText;
    }

    @Override
    public String getTaskTime() {
        return this.taskTime.format(Task.OUTPUT_TIME_FORMAT);
    }

    @Override
    public TaskType getTaskType() {
        return this.TASK_TYPE;
    }

}
