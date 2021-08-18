package tasks;

public class Event extends Task {
    private final String at;

    public Event(String eventDataText) {
        String[] eventData = eventDataText.split("/at ", 2);
        super.setDescription(eventData[0].trim());
        this.at = eventData[1].trim();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
