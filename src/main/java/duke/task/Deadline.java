package duke.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * This class implements a Deadline object which inherits from Task and has a dueBy info.
 *
 * CS2103T ip
 * AY21/22 Semester 1
 *
 * @author Kishendran Vendar Kon (Group G05)
 */
public class Deadline extends Task {
    /** String representing dueBy date/time of Deadline. */
    protected static SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyyy hh:mm aaa");
    protected Calendar dueByCalendar;


    /** Default constructor. */
    public Deadline(String description, Calendar dueByCalendar) {
        super(description);
        this.dueByCalendar = dueByCalendar;
    }

    /**
     * Returns the string representation of Deadline
     *
     * @return The string representation of Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + formatter.format(dueByCalendar.getTime()) + ")";
    }

}
