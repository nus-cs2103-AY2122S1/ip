import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class IS-A Task.
 *
 * Task that needs to be done by a specific date/time
 * @author Timothy Wong Eu-Jin
 * @version A-MoreOOP
 */
public class Deadline extends Task {

    private LocalDate date;

    public Deadline(String description, String date) {
        super(description);
        this.date = LocalDate.parse(date);
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String toString() {
        return ("[D]" + super.toString() + " (by: " +
                this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
    }

}