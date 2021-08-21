import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

/**
 * Deadline class, subclass of Task
 * Encapsulates Task with end time
 */
public class Deadline extends Task {

    private final String dateTime;
    private final LocalDate date;
    private final LocalTime time;

    /**
     * The constructor for the Deadline class
     * @param description The description of the object
     * @param dateTime The date given by the user
     */
    public Deadline(String description, String dateTime, boolean isDone) {
        super(description.trim(), isDone);
        this.dateTime = (dateTime = dateTime.trim());
        String[] splitByWhiteSpace = dateTime.split(" ");
        this.date = parseDate(splitByWhiteSpace[0]);
        this.time = parseTime(splitByWhiteSpace[1]);
    }

    @Override
    public String strForSaving() {
        return "D|" + this.getStatusIcon() + "|" + this.description + "|" + dateTime;
    }
    
    public LocalTime parseTime(String time)  {
        String formattedTime = time.indexOf(":") > 0 ? time
                                                     : time.substring(0, 2) + ":" + time.substring(2);
        return LocalTime.parse(formattedTime);
    }

    public LocalDate parseDate(String date) {
        String[] ddMMYY = date.split("/");
        if (ddMMYY[0].length() == 1) { //if user types 2/12/2019 -> 02/12/2019
            ddMMYY[0] = "0" + ddMMYY[0];
        }
        return LocalDate.parse(ddMMYY[2] + "-" + ddMMYY[1] + "-" + ddMMYY[0]);
    }

    /**
     * The overridden toString method for the Deadline class
     * @return String representation of object
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
                                                     .format(date) +  " " + this.time + ")";
    }
}
