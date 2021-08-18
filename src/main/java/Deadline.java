public class Deadline extends Task{
  private String dateLine;

  public Deadline(String description, String dateLine) {
    super(description);
    this.dateLine = dateLine;
  }

  @Override
  public String getStatusIcon() {
    return "[D]" + super.getStatusIcon();
  }

  @Override
  public String getDescription() {
    return String.format("%s(by:%s)", super.getDescription(), this.dateLine);
  }

}
