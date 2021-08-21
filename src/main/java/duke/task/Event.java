package duke.task;

import duke.util.Parser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class encapsulates an duke.task.Event.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class Event extends Task {
    private final String dateTimeString;
    private final Date dateTime;

    /**
     * Instantiates a new duke.task.Event.
     *
     * @param description the description of the event.
     * @param dateTimeString the datetime of event.
     */
    public Event(String description, String dateTimeString) {
        super(description);
        this.dateTimeString = dateTimeString;
        this.dateTime = Parser.parseDateTime(dateTimeString);
    }

    private String dateTimeToString() {
        if (dateTime == null) {
            return dateTimeString;
        }

        DateFormat outFormat;
        if (this.dateTimeString.split(" ").length == 2) {
            outFormat = new SimpleDateFormat("MMM dd yyyy h.mm aa");
        } else {
            outFormat = new SimpleDateFormat("MMM dd yyyy");
        }
        return outFormat.format(this.dateTime);
    }

    @Override
    public String convertToTxt() {
        return String.format("E | %s | %s", super.convertToTxt(), dateTimeToString());
    }

    /**
     * String representation of an duke.task.Event.
     *
     * @return String representation of an duke.task.Event.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.dateTimeToString());
    }
}
