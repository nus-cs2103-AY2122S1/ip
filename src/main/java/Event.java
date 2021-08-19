public class Event extends Task {
    private String eventDate;

    public Event(String description) {
        super(description);
    }

    public Event(String description, String eventDate) {
        super(description);
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        String result = "[E]" + super.toString();
        if (eventDate != null) {
            result += " (at: " + this.eventDate + ")";
        }
        return result;
    }
}
