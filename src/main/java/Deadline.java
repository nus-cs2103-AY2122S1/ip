import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    
    /** The deadline date and time of this task */
    protected LocalDateTime by;

    /** The accepted format of date and time for the deadline */
    private final String inputPattern = "dd-MM-yyyy HH:mm";

    public Deadline(String description, String by) throws DukeException {
        super(description);
        if (description == "") {
            throw new DukeException("Looks like you forgot to include a description of the deadline.");
        }
        if (by == "") {
            throw new DukeException("Looks like you forgot to include a deadline for the task.");
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(inputPattern);
            this.by = LocalDateTime.parse(by, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("The deadline date is invalid. Please follow this format: " + inputPattern);
        }
    }

    /** Returns the deadline date and time as a string to be displayed */
    private String getDateString() {
        return by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a")).replace("AM", "am").replace("PM","pm");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDateString()  + ")";
    }

    @Override
    public String toDataString(String delimiter) {
        String tag = "D";
        String done = super.isDone ? "1" : "0";
        return String.join(delimiter, tag, done, super.description, by.format(DateTimeFormatter.ofPattern(inputPattern)));
    }
}
