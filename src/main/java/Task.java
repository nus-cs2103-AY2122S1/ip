public abstract class Task {
  public boolean done = false;

  public void markDone() {
    this.done = true;
  }

  public boolean getDone() {
    return this.done;
  }

  abstract String getTaskDesc();
}
