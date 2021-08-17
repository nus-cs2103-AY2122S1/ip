public class Event extends Task{
    private final String eventDate;

    public Event(String description, String eventDate) {
        super(description, "E");
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (at: %s)", eventDate); // No preposition
    }
}
