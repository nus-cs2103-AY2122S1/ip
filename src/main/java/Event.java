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

    public void addTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + (time != null ? String.format(" (at:%s)", time) : "");
    }
}
