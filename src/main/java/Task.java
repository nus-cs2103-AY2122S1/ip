/**
 * Task class that encapsulate task behaviour and data.
 */
public class Task {

  private final String title;
  private boolean isComplete = false;

  Task(String title) {
    this.title = title;
  }

  /**
   * Set a task to be completed.
   *
   * @param isComplete new completion status
   */
  public void markComplete(boolean isComplete) {
    this.isComplete = isComplete;
  }

  @Override
  public String toString() {
    return isComplete ? "[x]" : "[ ] " + this.title;
  }
}
