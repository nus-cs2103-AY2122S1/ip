public class EventTask extends Task {
  private String taskText;
  private String taskDetail;

  public EventTask(String taskText, String taskDetail) {
    this.taskText = taskText.trim();
    this.taskDetail = taskDetail.trim();
  }

  @Override
  String getTaskDesc() {
    return String.format("%s (at: %s)", taskText, taskDetail);
  }

  @Override
  String getTaskSymbol() {
    return "E";
  }
  
}
