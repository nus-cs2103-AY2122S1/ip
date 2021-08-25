package tasks;

public class Event extends Task {
    private String eventTime;

    private Event(String description, boolean completed, String eventTime) {
        super(description, completed);
        this.eventTime = eventTime;
    }

    public static Event addEvent(String input, boolean completed) {
        String[] arr = input.split(" /at ");

        Event item = new Event(arr[0], completed, arr[1]);

        return item;
    }

    @Override
    public String format() {
        String format = "E | ";

        if (this.isDone) {
            format += "0 | ";
        } else {
            format += "1 | ";
        }

        format += this.description;
        format += " | ";
        format += this.eventTime;

        return format;
    }

    @Override
    public String toString() {
        String time = "(at: " + this.eventTime + ")";

        String res = "[E] [" + this.getStatus() + "] " + this.description + " " + time;

        return res;
    }
}
