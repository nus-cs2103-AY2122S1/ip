public class Event extends Task {

    private String time;

    public Event(String eventName) {
        super(eventName.substring(6, eventName.indexOf("/at")));
        int start = eventName.indexOf("/at");
        this.time = eventName.substring(start + 4);
    }

    public Event(String eventName, boolean isDone) {
        super(eventName.substring(0, eventName.indexOf("(at:")), isDone);
        this.time = eventName.substring(eventName.indexOf("(at:") + 5,
                eventName.length() - 1);
    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + "(at: "
                + this.time
                + ")";
    }
}
