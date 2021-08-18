public class Task {

  private String description;
  private boolean done;

  public Task(String description) {
    this.description = description;
    this.done = false;
  }

  public void markAsDone() {
    this.done = true;
  }

  public boolean getDone() {
    return this.done;
  }

  public String toString() {
    String output = "";
    if (this.done) {
      output += "[X] ";
    } else {
      output += "[] ";
    }
    output += this.description;
    return output;
  }
}
