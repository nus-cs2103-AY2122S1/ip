public class Event extends Task{
    private String eventDatetime;

    public Event(String text, String eventDatetime) {
        super(text);
        this.eventDatetime = eventDatetime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.eventDatetime);
    }
}
