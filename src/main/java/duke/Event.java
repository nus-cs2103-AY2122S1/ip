package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    /**
     * String representation of date of duke.Event.
     */
    protected LocalDate date;
    protected String time;

    /**
     * Constructor for duke.Event object.
     * @param description Description of duke.Event.
     */
    public Event(String description, LocalDate ld, String time) {
        super(description);
        this.date = ld;
        this.time = time;
        this.type = TaskType.EVENT;
    }

    /**
     * Returns string representation of duke.Event object.
     * @return String representation of duke.Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " +  this.date.format(DateTimeFormatter.ofPattern("d MMM yyyy ")) + this.time + ")";
    }

    /**
     * Returns at property of duke.Event object.
     * @return at of duke.Event.
     */
    public String getAt() {
        return this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " " + this.time;
    }
}
