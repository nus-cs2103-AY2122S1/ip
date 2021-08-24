package tasks;

import java.time.LocalDateTime;

import bot.TaskType;

public class EventTask extends Task {

  private String taskText;
  private LocalDateTime taskTime;
  private TaskType taskType = TaskType.Event;

  public EventTask(String taskText, LocalDateTime taskTime) {
    this.taskText = taskText.trim();
    this.taskTime = taskTime;
  }

  @Override
  String getTaskDesc() {
    return String.format("%s (at: %s)", taskText, getTaskTime());
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
    return this.taskType;
  }

}
