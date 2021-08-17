public class Event extends Task {
    protected String time;

    public Event(String description, String time) {
        super(description, Task.Type.E);
        this.time = time;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + time + ")";
    }
}
