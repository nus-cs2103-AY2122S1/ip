public class Event extends Task{
    /* likely needed for future implementation
    protected String startTime;
    protected String endTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }
     */
    protected String deadline;

    public Event(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (at: %s)", deadline);
    }
}
