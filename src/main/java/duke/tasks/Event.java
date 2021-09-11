package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Event class encapsulates an event with an event time.
 */
public class Event extends Task {
    /** Event time stored as a LocalDate object */
    private LocalDate timePeriod;

    public Event(String taskName, String timePeriod) {
        super(taskName);
        this.timePeriod = LocalDate.parse(timePeriod);
    }

    /**
     * Returns a formatted time period of the event.
     *
     * @return A string of the event time period.
     */
    public String getTimePeriod() {
        return this.timePeriod.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String toString() {
        return "Event: " + super.toString() + " (at "
                + this.timePeriod.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
