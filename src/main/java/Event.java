public class Event extends Task{
  private String eventTime;

  public Event(String description, String eventTime) {
    super(description);
    this.eventTime = eventTime;
  }

  @Override
  public String getStatusIcon() {
    return "[E]" + super.getStatusIcon();
  }

  @Override
  public String getDescription() {
    return String.format("%s(at:%s)", super.getDescription(), this.eventTime);
  }
}
