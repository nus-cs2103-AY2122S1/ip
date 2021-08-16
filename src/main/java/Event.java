public class Event extends Task {

    protected String at;

    public Event(String description, String timingString) {
        super(description);
        this.at = timingString;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

}
