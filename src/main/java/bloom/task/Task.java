package bloom.task;

import java.util.ArrayList;

public class Task {
  public static ArrayList<Task> tasks = new ArrayList<>();
  private final String description;
  private boolean isDone;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  @Override
  public String toString() {
    return "[" + getStatusIcon() + "] " + this.description;
  }

  public String getStatusIcon() {
    return (this.isDone ? "X" : " ");
  }

  public void markAsDone() {
    this.isDone = true;
  }
}
