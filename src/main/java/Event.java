public class Event extends Task{
    protected String deadline;
    protected String logo = "[E]";

    public Event(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public String logo() {
        return logo;
    }

    @Override
    public String toString() {
        return "[E]" + super.getStatusIcon() +  " " + super.description + " (at: " + deadline + ")";
    }
}