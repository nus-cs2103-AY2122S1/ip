public class Event extends Task{
    public Event(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return String.format("[E]%s", super.toString());
    }
}
