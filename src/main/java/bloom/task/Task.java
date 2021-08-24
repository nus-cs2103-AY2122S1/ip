package bloom.task;

public class Task {

  protected final String description;
  
  protected boolean isDone;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  @Override
  public String toString() {
    return "[" + this.getStatusIcon() + "] " + this.description;
  }

  public String toDb() {
    return (this.isDone ? 1 : 0) + " | " + this.description;
  }

  public String getStatusIcon() {
    return (this.isDone ? "X" : " ");
  }

  public void markAsDone() {
    this.isDone = true;
  }
}
