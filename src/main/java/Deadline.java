import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline encapsulates the name of the deadline as well as
 * when the task needs to be completed by.
 */

public class Deadline extends Task {
    private LocalDate doneBy;

    /**
     * Constructs Deadline object with given name, and deadline of the task.
     * @param name The name of the Deadline
     * @param by Deadline of the task
     */
    public Deadline(String name, String by) {
        super(name);

        // formatting deadlines
//        LocalDate date = LocalDate.parse(by);
//        this.doneBy = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.doneBy = LocalDate.parse(by);
    }

    /**
     * Converts the deadline object into a string
     * @return A string containing the type (Deadline), completion status and time
     * of the deadline to be done by.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + doneBy.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
