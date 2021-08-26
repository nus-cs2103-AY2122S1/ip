public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description, 'E');
        this.at = at;
    }

    @Override
    public String getDueTime() {
        return at;
    }

    @Override
    public String toString() {
        return "[" + this.getCat() + "]" + super.toString() + " (at: " + at + ")";
    }
}