package tasks;

public class Event extends Task {
    private String eventTime;

    private Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    public static Event addEvent(String input) {
        String[] arr = input.split(" /at ");

        Event item = new Event(arr[0], arr[1]);

        return item;
    }

    @Override
    public String toString() {
        String time = "(at: " + this.eventTime + ")";

        String res = "[E] [" + this.getStatus() + "] " + this.description + " " + time;

        return res;
    }
}
