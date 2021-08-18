public class Event extends Task {
    protected String day;
    protected String time;

    public Event(String name, String day, String time) {
        super(name);
        this.day = day;
        this.time = time;

    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + day + " " + time + ")";
    }
}
