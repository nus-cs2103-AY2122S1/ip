public class Event extends Task{
    private String startDate;

    public Event(String description, String startDate) {
        super(description);
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
