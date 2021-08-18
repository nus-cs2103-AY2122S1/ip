public class Event extends Task {

    private String datetime;

    public Event(String description, String time) {
        super(description);
        datetime = time;
    }

    @Override
    public String toString() {
        String eventMarker = "[E]";
        String timestamp = String.format("(%s: %s)", datetime.split(" ", 2)[0], datetime.split(" ", 2)[1]);
        if (isDone) {
            return eventMarker + hasCross + " " + item + " " + timestamp;
        } else {
            return eventMarker + hasNoCross + " " + item + " " + timestamp;
        }
    }
}
