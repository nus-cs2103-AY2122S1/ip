public class Event extends Task {

    protected String at;

    public Event(String title, String at) {
        super(title);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + this.getStatusIcon() + "] " + this.title + " (at: " + at + ")";
    }
}
