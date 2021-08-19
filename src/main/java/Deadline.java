import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This is a Deadline class that extends Task.
 */
public class Deadline extends Task {

    protected LocalDate date;
    protected LocalTime time;

    public Deadline(String description, String by) throws CommandParamException {
        super(description);
        String[] dateTime = by.trim().split(" ");
        //index 0 is date, 1 is time
        //date should be yyyy-mm-dd, time should be 2359...
        if (dateTime.length <= 1 || dateTime.length > 2) {
            throw new CommandParamException("Deadline");
        }
        try {
            String timeReformatted = dateTime[1].substring(0, 2) + ":" + dateTime[1].substring(2, 4);
            LocalDate date = LocalDate.parse(dateTime[0].trim());
            LocalTime time = LocalTime.parse(timeReformatted);
            this.date = date;
            this.time = time;
        } catch (DateTimeParseException e) {
            throw new CommandParamException("deadline");
        }
    }

    @Override
    public String fullCommand() {
        return "deadline " + this.description + " /by "
                           + this.date.toString()
                           + " "
                           + this.time.format(DateTimeFormatter.ofPattern("kkmm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " "
                + this.time.format(DateTimeFormatter.ofPattern(("h:mma")))
                + ")";
    }
}
