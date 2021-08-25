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
    String getTaskDesc() {
        return String.format("%s (by: %s)", taskText, getTaskTime());
    }

    @Override
    String getTaskText() {
        return this.taskText;
    }

    @Override
    String getTaskTime() {
        return this.taskTime.format(Task.OUTPUT_TIME_FORMAT);
    }

    @Override
    TaskType getTaskType() {
        return this.TASK_TYPE;
    }

}
