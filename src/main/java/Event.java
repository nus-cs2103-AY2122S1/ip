public class Event extends Task {
    private String eventDate;
    /**
     * Constructor for the Deadline task.
     * @param name Name of the Task.
     * @param eventDate Date of the Event.
     */
    public Event(String name, String eventDate) {
        super(name);
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventDate + ")";
    }
}
