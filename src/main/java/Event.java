public class Event extends Task {

  private String date;

  public Event(String description, String date) {
    this.description = description;
    this.date = date;
    this.done = false;
  }

  public String toString() {
    String output = "[E]";
    if (this.done) {
      output += "[X] ";
    } else {
      output += "[] ";
    }
    output += this.description;
    output += String.format(" (at: %s)", this.date);
    return output;
  }
}
