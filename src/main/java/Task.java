public class Task {

  private String description;
  private boolean done;

  public Task(String description) {
    this.description = description;
    this.done = false;
  }

  private String getStatusIcon() {
    return (done ? "[X]" : "[ ]"); // mark done task with X
  }

  protected void markDone() {
    done = true;
  }

  protected boolean isDone() {
    return done;
  }

  @Override
  public String toString() {
    return getStatusIcon() + " " + description;
  }
}
