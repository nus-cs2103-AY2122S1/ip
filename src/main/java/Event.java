public class Event extends Task {

    public Event(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
