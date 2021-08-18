public class Event extends Task{

    public Event(String name) {
        super(name);
    }

    public String toString() {
        return "[E]" + super.toString();
    }
}
