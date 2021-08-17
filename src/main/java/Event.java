public class Event extends Task{
    public Event(String label) {
        super(label);
    }

    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
