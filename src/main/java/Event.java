public class Event extends Task {

    private String startTime;

    public Event(String str, String startTime) {
        super(str);
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + startTime + ")";
    }
}
