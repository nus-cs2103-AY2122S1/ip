public class Event extends Task {
    protected String at;

    public Event(String taskstr, String at) {
        super(taskstr);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + this.at + ")";
    }
}
