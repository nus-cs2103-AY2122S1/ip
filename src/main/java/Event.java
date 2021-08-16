public class Event extends Item {

    public Event(String[] strings) {
        super(strings);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
