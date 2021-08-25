import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        if (description == "") {
            throw new DukeException("Looks like you forgot to include a description of the deadline.");
        }
        if (by == "") {
            throw new DukeException("Looks like you forgot to include a deadline for the task.");
        }
        String inputPattern = "dd-MM-yyyy HH:mm";
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(inputPattern);
            this.by = LocalDateTime.parse(by, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("The deadline date is invalid. Please follow this format: " + inputPattern);
        }
    }
    
    private String getDateString() {
        return by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDateString()  + ")";
    }

    @Override
    public String toDataString(String delimiter) {
        String tag = "D";
        String done = super.isDone ? "1" : "0";
        return String.join(delimiter, tag, done, super.description, by);
    }
}
