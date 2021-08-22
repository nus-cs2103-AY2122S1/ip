public class Task {
  protected String description;
  protected boolean isDone;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  private String getStatusIcon() {
    return isDone ? "X" : " ";
  }

  public void markAsDone() {
    this.isDone = true;
  }

  public String toStringData() {
    return (this.isDone ? "1" : "0") + " | " + this.description;
  }

  @Override
  public String toString() {
    return "[" + this.getStatusIcon() + "] " + this.description;
  }
}
