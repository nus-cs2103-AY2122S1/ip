public class Task {
  private String name;
  private boolean completed;

  public Task(String name) {
    this.name = name;
    this.completed = false;
  }

  public void markAsDone() {
    this.completed = true;
  }

  @Override
  public String toString() {
    String symbol = completed ? "X" : " ";
    return "[" + symbol + "] " + name;
  }
}
