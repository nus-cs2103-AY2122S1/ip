public class Event extends Task {
    private String time;

    public Event(String rawTime) {
        super(rawTime.split("/", 2)[0]);
        String[] procTime = rawTime.split("/", 2);
        String time = procTime[1];
        time = "(" + time + ")";
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + time;
    }
}
