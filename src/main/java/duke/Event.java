package duke;

public class Event extends Task {

  private String date;

  public Event(String description, String date) {
    this.description = description;
    this.date = date;
    this.done = false;
  }

  public Event(String description, String date, boolean done) {
    this.description = description;
    this.date = date;
    this.done = done;
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

  /**
   * Returns proper format to write to txt file.
   */
  public String toWriteString() {
    String output = Duke.EVENT_ENUM;
    String done = (this.done ? "1" : "0");
    output += DIVIDER + done + DIVIDER + this.description + DIVIDER + this.date;
    return output;
  }
}
