public class Event extends Task {
    protected String timeframe;

    public Event(String description, String timeframe) {
        super(description);
        this.timeframe = timeframe;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.timeframe + ")";
    }
}