public class DeadlineTask extends Task {
  private String taskText;
  private String taskDetail;

  public DeadlineTask(String taskText, String taskDetail) {
    this.taskText = taskText.trim();
    this.taskDetail = taskDetail.trim();
  }

  @Override
  String getTaskDesc() {
    return String.format("%s (by: %s)", taskText, taskDetail);
  }

  @Override
  String getTaskSymbol() {
    return "D";
  }
  
}
