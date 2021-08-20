public class Event extends Task {
    private String time = null;

    public Event() {}
    public Event(String desc) {
        super(desc);
    }
    public Event(String desc, String time) {
        super(desc);
        this.time = time;
    }
    public Event(String desc, String time, boolean done) {
        super(desc, done);
        this.time = time;
    }

    public void addTime(String time) {
        this.time = time;
    }

    public String toDB() {
        return String.format("E | %d | %s | %s", super.done ? 1 : 0, super.desc, time);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + (time != null ? String.format(" (at:%s)", time) : "");
    }
}
