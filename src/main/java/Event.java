public class Event extends Task{
    private String startDate;

    public Event(String description, Boolean completionStatus, String startDate) {
        super(description, completionStatus);
        this.startDate = startDate;
    }

    @Override
    public String typeIcon() {
        return "[E]";
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + startDate + ")";
    }
}
