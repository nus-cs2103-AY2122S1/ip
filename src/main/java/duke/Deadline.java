package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    /**
     * String representation of due date of Deadline.
     */
    protected LocalDate date;
    protected String time;

    /**
     * Constructor for Deadline object.
     * @param description Description of Deadline.
     */
    public Deadline(String description, LocalDate ld, String time) {
        super(description);
        this.date = ld;
        this.time = time;
        this.type = TaskType.DEADLINE;
    }

    /**
     * Returns string representation of Deadline object.
     * @return String representation of Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " +  this.date.format(DateTimeFormatter.ofPattern("d MMM yyyy ")) + this.time + ")";
    }

    public String getBy() {
        return this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " " + this.time;
    }
}