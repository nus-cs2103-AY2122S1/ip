public class Event extends Task {
    protected String at;

    public Event(String description, String at) throws IllegalArgumentException {
        super(description);
        if (at.equals("")) {throw new IllegalArgumentException();}
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}