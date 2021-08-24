package tasks;

import bot.TaskType;

public class DeadlineTask extends Task {

  private String taskText;
  private String taskDetail;
  private TaskType taskType = TaskType.Deadline;

  public DeadlineTask(String taskText, String taskDetail) {
    this.taskText = taskText.trim();
    this.taskDetail = taskDetail.trim();
  }

  @Override
  String getTaskDesc() {
    return String.format("%s (by: %s)", taskText, taskDetail);
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
