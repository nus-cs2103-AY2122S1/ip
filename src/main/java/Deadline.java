import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends DateTimeTask {

    public final String code = "D";
    private final LocalDateTime by;

    public Deadline(String[] input) throws DukeException, DateTimeParseException {
        super(input[0]);
        if (input.length != 2) {
            throw new DukeException(DukeException.Type.DEADLINE);
        }
        this.by = LocalDateTime.parse(input[1]);
    }

    @Override
    public String toString() {
        return "[" + code + "]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")) + ")";
    }

    @Override
    public LocalDateTime getDateTime() {
        return this.by;
    }

    @Override
    public String getCode() {
        return this.code;
    }
}