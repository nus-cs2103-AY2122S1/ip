public class Event extends Task {
    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return ("[E]" + super.toString() + " (at: " + this.at + ")");
    }

    public String toStorageString() {
        return ("E|" + super.getStatusNumber() + "|" + super.getDescription()
                + "|" + this.at);
    }
}
