package bob.task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Event extends Task {
    /** Date of event */
    private LocalDate time;

    public Event(String description, String time) {
        super(description);
        this.time = LocalDate.parse(time);
     }

    @Override
    public String printTask() {
        return "[E] " + super.printTask() + " (at: " + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
