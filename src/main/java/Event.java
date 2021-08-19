public class Event extends Task {
    private String time;

    public Event (String desc, String time) {
        super(desc);
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.time + ")";
    }
}
