public class GeneralTask extends Task {

  private String description;

  public GeneralTask(String description) {
    this.description = description;
  }

  @Override
  public String getTaskDesc() {
    return description;
  }

  @Override
  String getTaskSymbol() {
    return "G";
  }
  
}
