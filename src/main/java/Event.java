public class Event extends Task {

    protected String at;

    public Event(String description, String at) throws DukeException1 {
        super(description);
        if(description.equals("event")) {
            throw new DukeException1();
        }
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
