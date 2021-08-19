public class Event extends Task{
    private String startTime;

    public Event(String name, String startTime) {
        super(name);
        this.startTime = startTime;
    }

    public String toString() {
        return "[E]" + super.toString() + "(at: " + startTime + ")";
    }
}
