public class Event extends Task {
    protected String timing;

    public Event(String desc, String timing) {
        super(desc);
        this.timing = timing;
    }

    @Override
    public String toString() {
        return "[E]" 
                + super.toString()
                + " (at: "
                + this.timing
                + ")";
    }
}
