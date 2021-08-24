package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task. A <code>Event</code> object corresponds to
 * a task represented by a date and time.
 */
public class Event extends Task {

    protected final LocalDate date;
    protected final String time;

    /**
<<<<<<< HEAD
     * Returns a <code>Duke</code> object that can reply to
     * commands. Duke can save the tasks at the specified <code>filePath</code>.
     * @param description The description of the event task.
     * @param ld The <code>LocalDate</code> object to represent
     *           the date of the event.
     * @param time The time that the event is happening.
=======
     * Constructor for duke.Event object.
     *
     * @param description Description of duke.Event.
>>>>>>> branch-A-CodingStandard
     */
    public Event(String description, LocalDate ld, String time) {
        super(description);
        this.date = ld;
        this.time = time;
        this.type = TaskType.EVENT;
    }

    /**
<<<<<<< HEAD
     * Returns string representation of <code>Event</code> object.
     * @return String representation of <code>Event</code> object.
=======
     * Returns string representation of duke.Event object.
     *
     * @return String representation of duke.Event.
>>>>>>> branch-A-CodingStandard
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date.format(DateTimeFormatter.ofPattern("d MMM yyyy ")) + this.time + ")";
    }

    /**
     * Returns at property of duke.Event object.
<<<<<<< HEAD
     * @return The date and time of the event.
=======
     *
     * @return at of duke.Event.
>>>>>>> branch-A-CodingStandard
     */
    public String getAt() {
        return this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " " + this.time;
    }
}
