import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructor for Deadline
     * @param input the input array consisting of description and date/time
     */
    public Deadline(String[] input) throws DukeException {
        super(input[0]);
        if (input.length < 2) throw new EmptyTimeException();
        try {
            this.by = LocalDateTime.parse(input[1], DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new InvalidTimeException();
        }
    }

    /**
     * Constructor for Deadline
     * @param input the input array consisting of description and date/time from saved data
     * @param isDone deadline's status from saved data
     */
    public Deadline(String[] input, boolean isDone) {
        super(input[0], isDone);
        this.by = LocalDateTime.parse(input[1]);
    }

    /**
     * Converts content to formatted text to save into storage
     * @return formatted text to save into storage
     */
    @Override
    public String convertToData() {
        return String.format("D/%s/%s/%s", this.isDone ? "1" : "0", this.description, this.by);
    }

    /**
     * Returns string representation of this deadline
     * @return string representation of this deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("d MMM yyyy h:mma")) + ")";
    }
}
