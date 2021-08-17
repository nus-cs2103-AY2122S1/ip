package tasks;

/**
 * Handles tasks that have no dates.
 */
public class TodoTask extends Task {

  public TodoTask(String title) {
    super(title, Type.TODO);
  }

  @Override
  public String toString() {
    return "[T] " + super.toString();
  }
}
