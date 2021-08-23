public class Deadline extends Task {

  protected String when;

  public Deadline(String description, String when) {
    super(description, Task.Type.DEADLINE);
    this.when = when;
  }

  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + when + ")";
  }
}
