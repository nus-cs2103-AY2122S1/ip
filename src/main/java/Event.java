public class Event extends Task {
    private String eventDetails;
    public Event(String taskDetails, String eventDetails) {
        super(taskDetails);
        this.eventDetails = eventDetails;
    }
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.eventDetails);
    }
}
