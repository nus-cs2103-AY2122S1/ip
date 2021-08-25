public class Event extends Task {

    public Event(String description, String at) {
        super(description, at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + super.time.format(TIME_FORMAT) + ")";
    }
}
