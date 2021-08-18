public class Deadline extends Task {
  private String due;

  public Deadline(String name, String due) {
    super(name);
    this.due = due;
  }

  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + due + ")";
  }
}
