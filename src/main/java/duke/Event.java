package duke;

public class Event extends Item {
    private String time;

    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String getPickle() {
        return super.getPickle() + "|" + this.time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + time + ")";
    }
}
