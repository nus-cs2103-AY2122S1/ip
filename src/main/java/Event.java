public class Event extends Task {

  private String eventDate;

  Event(String title, String eventDate) {
    super(title);
    this.eventDate = eventDate;
  }

  @Override
  public String toString() {
    if (this.done) {
      return "[E][ ] " + this.title + "(at: " + this.eventDate + ")";
    } else {
      return "[E][X] " + this.title + "(at: " + this.eventDate + ")";
    }
  }
}
