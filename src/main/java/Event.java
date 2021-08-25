/**
 * A task with a description and a start and end date/time
 */
public class Event extends Task {

    private String eventDateTime;

    Event(String description, boolean completed, String eventDateTime) {
        super(description, completed);
        this.eventDateTime = eventDateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (at: %s)", eventDateTime);
    }

    @Override
    public String encode() {
        return "e" + DukeMemory.DELIMITER
                + (this.isCompleted() ? "1" : "0") + DukeMemory.DELIMITER
                + eventDateTime + DukeMemory.DELIMITER
                + this.getDescription();
    }
}
