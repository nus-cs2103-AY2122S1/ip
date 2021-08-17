public class Event extends Task {

    private String timeFrame;

    public Event (String description, String timeFrame) {
        super(description);
        this.timeFrame = timeFrame;

    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.timeFrame);
    }
}
