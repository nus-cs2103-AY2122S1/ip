package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Tasks that start at a specific time and ends at a specific time
 */
public class Event extends Task{
    private LocalDate startTime;

    public Event(String name, LocalDate startTime) {
        super(name);
        this.startTime = startTime;
    }

    public String toString() {
        return "[E]" + super.toString() + "->at: " +
                startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String toStorageFormat() {
        return "[E]" + super.toString() + "->at: " + startTime;
    }

    @Override
    public void setTime(LocalDate time) throws DukeException {
        this.startTime = time;
    }
}
