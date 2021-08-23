public class Deadline extends Task {

  protected String when;

  public Deadline(String description, String when, boolean done) {
    super(description, Task.Type.DEADLINE, done);
    this.when = when;
  }

  @Override
  public String taskToString() {
    return super.taskToString() + when;
  }

  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + when + ")";
  }
}
