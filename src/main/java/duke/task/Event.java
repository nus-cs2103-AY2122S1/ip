package duke.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * This class implements a Deadline object which inherits from Task and additionally stores times of Event.
 *
 * CS2103T ip
 * AY21/22 Semester 1
 *
 * @author Kishendran Vendar Kon (Group G05)
 */
public class Event extends Task {
    /** String representing time of Event. */
    protected static SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyyy hh:mm aaa");
    protected Calendar atCalendar;


    /** Default constructor. */
    public Event(String description, Calendar atCalendar) {
        super(description);
        this.atCalendar = atCalendar;
    }

    /**
     * Returns the string representation of Event
     *
     * @return The string representation of Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + formatter.format(atCalendar.getTime()) + ")";
    }
}
