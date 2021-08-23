public class Event extends Task {

  protected String when;

  public Event(String description, String when, boolean done) {
    super(description, Task.Type.EVENT, done);
    this.when = when;
  }

  @Override
  public String taskToString() {
    return super.taskToString() + when;
  }

  @Override
  public String toString() {
    return "[E]" + super.toString() + " (at: " + when + ")";
  }
}
