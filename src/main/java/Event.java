public class Event extends Tasks {
    protected String at;

    public Event(String description) {
        super(description.substring(0, description.indexOf("/at")));
        this.at = description.substring(description.indexOf("/at ") + 4);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
