public class Event extends Task{
    public Event(String label) {
        super(label);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
