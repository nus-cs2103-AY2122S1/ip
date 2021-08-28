package duke.command;

// import duke packages
import duke.DukeException;
import duke.task.Task;

// import java packages
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    protected String byOriginal;
    protected LocalDate by;
    protected Date time;

    protected String dateToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return formatter.format(by);
    }

    protected String timeToString() {
        SimpleDateFormat displayFormat = new SimpleDateFormat("hh:mm a");
        return displayFormat.format(time);
    }

    public Deadline(String description, String by) throws DukeException {
        super(description, 'D');
        this.name = "deadline";
        byOriginal = by;

        String[] temp = by.split(" ", 2);
        try {
            this.by = LocalDate.parse(temp[0]);
        } catch (DateTimeParseException e) {
            throw new DukeException("time has to be in the format yyyy-mm-dd HHmm instead of " + by);
        }

        try {
            SimpleDateFormat parseFormat = new SimpleDateFormat("HHmm");
            this.time = parseFormat.parse(temp[1]);
        } catch (ParseException e) {
            throw new DukeException("time has to be in the format yyyy-mm-dd HHmm instead of " + by);
        }
    }

    @Override
    public String getDueTime() {
        return byOriginal;
    }

    @Override
    public String toString() {
        return "[" + this.getCat() + "]" + super.toString() + " (by: " + dateToString() + " " + timeToString() + ")";
    }
}