public class Event extends Task{

    private String at;

    public Event(String value, String at) {
        super(value);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
