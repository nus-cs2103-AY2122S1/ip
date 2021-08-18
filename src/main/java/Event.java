public class Event extends Entry{

    private String event;

    Event() {
        super();
    }

    Event(String task, String event) {
        super(task);
        this.event = event;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + event + ")";
    }

    @Override
    public boolean isEmpty() { return super.isEmpty() || event.length() < 1; }
}
