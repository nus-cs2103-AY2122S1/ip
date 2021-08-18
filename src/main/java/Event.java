public class Event extends Task {

    private String datetime;

    public Event(String description, String time) {
        super(description);
        datetime = time;
    }

    @Override
    public String toString() {
        String eventMarker = "[E]";
        String timestamp = String.format("(at: %s)", datetime);

        if (isDone) {
            return eventMarker + hasCross + " " + item + " " + timestamp;
        } else {
            return eventMarker + hasNoCross + " " + item + " " + timestamp;
        }
    }
}
