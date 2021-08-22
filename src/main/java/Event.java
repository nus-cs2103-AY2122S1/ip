public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String getReadableString() {
        String status = this.isDone ? "1" : "0";
        return "E | " + status + " | " + this.description + " | " + this.at  + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}