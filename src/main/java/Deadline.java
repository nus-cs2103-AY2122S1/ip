public class Deadline extends Task {

  private String date;

  public Deadline(String description, String date) {
    this.description = description;
    this.date = date;
    this.done = false;
  }

  public String toString() {
    String output = "[D]";
    if (this.done) {
      output += "[X] ";
    } else {
      output += "[] ";
    }
    output += this.description;
    output += String.format(" (by: %s)", this.date);
    return output;
  }
}
