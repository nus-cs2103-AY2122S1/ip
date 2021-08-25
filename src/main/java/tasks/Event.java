package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate eventTime;

    private Event(String description, String eventTime) {
        super(description);
        this.eventTime = LocalDate.parse(eventTime);
    }

    public static Event addEvent(String input) {
        String[] arr = input.split(" /at ");

        Event item = new Event(arr[0], arr[1]);

        return item;
    }

    @Override
    public String toString() {
        String date = this.eventTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String time = "(at: " + date + ")";

        String res = "[E] [" + this.getStatus() + "] " + this.description + " " + time;

        return res;
    }
}