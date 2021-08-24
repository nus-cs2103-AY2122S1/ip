package bob.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
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
