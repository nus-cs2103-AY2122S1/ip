public class Todo extends Task {

  public Todo(String description) {
    this.description = description;
    this.done = false;
  }

  public String toString() {
    String output = "[T]";
    if (this.done) {
      output += "[X] ";
    } else {
      output += "[] ";
    }
    output += this.description;
    return output;
  }
}
