import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task with a description and an end date/time
 */
public class Deadline extends Task {

    private LocalDate endDate;

    Deadline(String description, boolean completed, LocalDate endDate) {
        super(description, completed);
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +
                String.format(" (by: %s)", endDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
    }

    @Override
    public String encode() {
        return "d" + DukeMemory.DELIMITER
                + (this.isCompleted() ? "1" : "0") + DukeMemory.DELIMITER
                + endDateTime + DukeMemory.DELIMITER
                + this.getDescription();
    }
}
