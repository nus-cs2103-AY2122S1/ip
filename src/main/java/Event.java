public class Event extends Task {

    public Event(String description, String at) {
        super(description, at);
    }

    public String save() {
        return String.format("E | %s | %s", super.save(), at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + super.time.format(TIME_FORMAT) + ")";
    }
}
