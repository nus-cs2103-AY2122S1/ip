package duke;

public class Deadline extends Task {

  private String date;

  public Deadline(String description, String date) {
    this.description = description;
    this.date = date;
    this.done = false;
  }

  public Deadline(String description, String date, boolean done) {
    this.description = description;
    this.date = date;
    this.done = done;
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

  /**
   * Returns proper format to write to txt file.
   */
  public String toWriteString() {
    String output = Duke.DEADLINE_ENUM;
    String done = (this.done ? "1" : "0");
    output += DIVIDER + done + DIVIDER + this.description + DIVIDER + this.date;
    return output;
  }
}
