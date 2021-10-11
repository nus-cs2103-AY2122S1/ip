package duke;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Encapsulates an Event which is a subtype of Task.
 */
public class Event extends Task {

    private final Date eventTime;
    private final String dateInput;

    /**
     * Constructor for an DukePakage.Event.
     * @param description The description of th event.
     * @param dateInput The time of the event. (I only just realised that Events had a time and not location.
     */
    public Event(String description, String dateInput) {
        super(description);
        this.dateInput = dateInput;
        this.eventTime = stringToDate(dateInput);
    }


    /**
     * Converts a String input given by the user into a Date of a given format.
     * @param dateInput String of date of event given by user.
     * @return A Date object with the same details as what the user has given.
     */
    public Date stringToDate(String dateInput) {
        Date date;
        DateFormat inFormat;

        if (dateInput.split(" ").length == 2) {
            inFormat = new SimpleDateFormat("yyyy-MM-dd hhmm");
        } else {
            inFormat = new SimpleDateFormat("yyyy-MM-dd");
        }
        try {
            date = inFormat.parse(dateInput);
        } catch (ParseException e) {
            return null;
        }
        return date;
    }

    /**
     * Converts a Date deadline into a String
     * @return A String object of the Date
     */
    public String dateToString() {
        if (eventTime == null) {
            return dateInput;
        }

        DateFormat outFormat;
        if (this.dateInput.split(" ").length == 2) {
            outFormat = new SimpleDateFormat("MMM dd yyyy h.mm aa");
        } else {
            outFormat = new SimpleDateFormat("MMM dd yyyy");
        }
        return outFormat.format(this.eventTime);
    }

    /**
     * Converts the given DukePakage.Event into an appropriate format for txt file.
     * @return a String of the DukePakage.Event for input into a txt file.
     */
    @Override
    public String toTxt() {
        return String.format("E | %d | %s | %s",
                super.getIsDone() ? 1 : 0,
                super.getDescription(),
                dateToString());
    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (at: "
                + dateToString()
                + ")";
    }

}
