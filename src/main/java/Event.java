public class Event extends Task {
    String at;
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getShortForm() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + this.at + ")";
    }
}