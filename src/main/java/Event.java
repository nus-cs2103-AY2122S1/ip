public class Event extends Task {
    private String marker = "[E]";
    private String time;

    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

//    public String getMarker() {
//        return this.marker;
//    }

    @Override
    public String toString() {
        return this.marker + super.toString() + " (by:" + time + ")";
    }
}