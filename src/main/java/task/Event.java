package task;

public class Event extends Task {

    private String timing;

    public Event(String description, String timing) {
        super(description);
        this.timing = timing;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getFormattedTiming() {
        return " (at: " + timing + ")";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + getFormattedTiming();
    }
}
