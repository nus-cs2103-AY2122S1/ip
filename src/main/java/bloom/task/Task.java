package bloom.task;

import java.util.ArrayList;

public class Task {
  public static ArrayList<Task> tasks = new ArrayList<>();
  protected final String description;
  protected boolean isDone;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  @Override
  public String toString() {
    return "[" + getStatusIcon() + "] " + this.description;
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
