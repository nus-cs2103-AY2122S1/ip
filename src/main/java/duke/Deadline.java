package duke;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Encapsulates the Deadlines which is a subtype of Task.
 */
public class Deadline extends Task {
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    private final Date deadline;
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    private final String dateInput;

    /**
     * Constructor to create a Deadline.
     * @param description Description of the Deadline task.
     * @param dateInput Date of when Deadline is due entered by user in String format.
     */
    public Deadline(String description, String dateInput) {
        super(description);
        this.dateInput = dateInput;
        this.deadline = stringToDate(dateInput);
    }

    /**
     * Converts a String input given by the user into a Date of a given format.
     * @param dateInput String of date of deadline given by user.
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
        if (deadline == null) {
            return dateInput;
        }

        DateFormat outFormat;
        if (this.dateInput.split(" ").length == 2) {
            outFormat = new SimpleDateFormat("MMM dd yyyy h.mm aa");
        } else {
            outFormat = new SimpleDateFormat("MMM dd yyyy");
        }
        return outFormat.format(this.deadline);
    }

    /**
     * Converts the given DukePakage.Deadline into an appropriate format for txt file.
     * @return a String of the DukePakage.Deadline for input into a txt file.
     */
    @Override
    public String toTxt() {
        return String.format("D | %d | %s | %s",
                super.getIsDone() ? 1 : 0,
                super.getDescription(),
                dateToString());
    }

    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: " + dateToString()
                + ")";
    }
}
