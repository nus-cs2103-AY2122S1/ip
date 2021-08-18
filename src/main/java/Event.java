public class Event extends Task {
    private String dateAndTime;
    /**
     * Constructor for Event
     *
     * @param description is the string of the description of the given task
     */
    public Event(String description, String dateAndTime) {
        super(description, "event");
        this.dateAndTime = dateAndTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (On: " + this.dateAndTime + ")";
    }
}