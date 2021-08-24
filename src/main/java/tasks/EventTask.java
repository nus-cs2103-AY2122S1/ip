package tasks;

import bot.TaskType;

public class EventTask extends Task {

  private String taskText;
  private String taskDetail;
  private TaskType taskType = TaskType.Event;

  public EventTask(String taskText, String taskDetail) {
    this.taskText = taskText.trim();
    this.taskDetail = taskDetail.trim();
  }

  @Override
  String getTaskDesc() {
    return String.format("%s (at: %s)", taskText, taskDetail);
  }
  
  @Override
  String getTaskText() {
    return this.taskText;
  }

  @Override
  String getTaskTime() {
    return this.taskDetail;
  }

  @Override
  TaskType getTaskType() {
    return this.taskType;
  }

}
