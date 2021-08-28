package duke.task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import duke.util.Parser;

/**
 * This class encapsulates an Event.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class Event extends Task {
    private final String dateTimeString;
    private final Date dateTime;

    /**
     * Instantiates a new Event.
     *
     * @param description    the description of the event.
     * @param dateTimeString the datetime of event.
     */
    public Event(String description, String dateTimeString) {
        super(description);
        this.dateTimeString = dateTimeString;
        this.dateTime = Parser.parseDateTime(dateTimeString);
    }

    /**
     * Reformats the dateTime format into a more readable format to be displayed.
     * Returns the formatted string.
     *
     * @return Formatted string after reformatting.
     */
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

    /**
     * Checks whether the date and time user input is the same as the deadline of task.
     *
     * @param dateTime the date and time that the user input.
     * @return true if deadline of task is the same as date and time of user input.
     */
    public boolean isSameDateTime(String dateTime) {
        return this.dateTime.equals(Parser.parseDateTime(dateTime));
    }

    /**
     * Converts the event task into text format meant for persisted storage.
     * Returns the formatted string.
     *
     * @return Formatted string of task meant for persisted storage.
     */
    @Override
    public String convertToTxt() {
        return String.format("E | %s | %s", super.convertToTxt(), dateTimeToString());
    }

    /**
     * String representation of an Event.
     *
     * @return String representation of an Event.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.dateTimeToString());
    }
}
