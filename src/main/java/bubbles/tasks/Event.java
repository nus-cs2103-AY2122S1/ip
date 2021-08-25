package bubbles.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate eventTime;

    private Event(String description, boolean completed, String eventTime) {
        super(description, completed);
        this.eventTime = Task.formatDate(eventTime);
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
            format += "1 | ";
        } else {
            format += "0 | ";
        }

        format += this.description;
        format += " | at ";
        format += this.eventTime;

        return format;
    }

    @Override
    public String toString() {
        String date = this.eventTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String time = "(at: " + date + ")";

        String res = "[E] [" + this.getStatus() + "] " + this.description + " " + time;

        return res;
    }
}