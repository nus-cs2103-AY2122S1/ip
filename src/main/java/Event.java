import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class Event extends Task {

    protected LocalDate at;
    protected Date time;

    protected String dateToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return formatter.format(at);
    }

    protected String timeToString() {
        SimpleDateFormat displayFormat = new SimpleDateFormat("hh:mm a");
        return displayFormat.format(time);
    }

    public Event(String description, String at) throws DukeException {
        super(description);
        String[] temp = at.split(" ", 2);
        try {
            this.at = LocalDate.parse(temp[0]);
        } catch (DateTimeParseException e) {
            throw new DukeException("time has to be in the format yyyy-mm-dd HHmm instead of " + at);
        }

        try {
            SimpleDateFormat parseFormat = new SimpleDateFormat("HHmm");
            this.time = parseFormat.parse(temp[1]);
        } catch (ParseException e) {
            throw new DukeException("time has to be in the format yyyy-mm-dd HHmm instead of " + at);
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateToString() +  " " + timeToString() + ")";
    }
}