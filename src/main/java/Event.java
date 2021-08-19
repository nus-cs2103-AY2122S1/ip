public class Event extends Task {

    protected String at;

    public Event(String description, String at){
        super(description);
        this.at = at;
        this.description = description;
    }

    @Override
    public String toString() {
        return "\t[E]" + super.toString() + " (at: " + at + ")";
    }
}
