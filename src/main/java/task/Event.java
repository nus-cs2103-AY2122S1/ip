package task;

public class Event extends Task {

    public final static String SPLITTER = "at";

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
        return " (" + SPLITTER + ": " + timing + ")";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + getFormattedTiming();
    }
}
