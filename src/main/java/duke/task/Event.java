package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime time;

    /**
     * Public constructor for the Event class
     * @param description description of the Event
     * @param time time of the event 
     */
    public Event(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + time.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mma")) + ")";
    }
}
