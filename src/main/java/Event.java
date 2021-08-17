public class Event extends Task{
    private String event;

    Event(String name, boolean done, String event) {
        super(name, done);
        this.event = event;
    }

    @Override
    public String toString() {
        if(this.isDone()) {
            return String.format("[E][X] %s (at: %s)", this.getName(), this.event);
        } else {
            return String.format("[E][ ] %s (at: %s)", this.getName(), this.event);
        }
    }
}
