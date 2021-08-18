public abstract class Task {
  public boolean done = false;

  public void markDone() {
    this.done = true;
  }

  public boolean getTaskDone() {
    return this.done;
  }

  @Override
  public String toString() {
    String taskChecked = this.done ? "X" : " ";
    return String.format("[%s] %s", taskChecked, this.getTaskDesc());
  } 

  abstract String getTaskDesc();
  abstract String getTaskSymbol();
}
