public class TodoTask extends Task {

  private String taskDesc;

  public TodoTask(String description) {
    this.taskDesc = description.trim();
  }

  @Override
  String getTaskDesc() {
    return taskDesc;
  }

  @Override
  String getTaskSymbol() {
    return "T";
  }
  
}
