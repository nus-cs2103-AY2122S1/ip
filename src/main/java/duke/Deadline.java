package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {

    protected String by;
    protected LocalDate date;

    /**
     * Constructor for a Deadline object.
     *
     * @param description the description of the deadline task.
     * @param by the due date of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        String[] d = by.split("-");
        if (d.length == 3) {
            date = LocalDate.parse(by);
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        if (this.date != null) {
            return "[D]" + super.toString() + " (by: "
                    + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }
}
