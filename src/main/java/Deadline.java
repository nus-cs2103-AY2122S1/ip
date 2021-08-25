import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A Task that needs to be done before a specific date/time.
 *
 * @author Lethicia
 */
public class Deadline extends Task{
    /** date/time by which task must be done. */
    protected LocalDate by;

    /**
     * Constructor for a Deadline task.
     *
     * @param description the title or description for the task
     * @param by date/time by which task must be done.
     */
    public Deadline(String description, String by) {
        super(description);
        try {
            LocalDate date = LocalDate.parse(by);
            this.by = date;
        } catch (DateTimeParseException e) {
            System.out.println("Incorrect date format.");
        }
    }

    /**
     * Constructor for a Deadline task.
     *
     * @param description the title or description for the task
     * @param by date/time by which task must be done.
     * @param status boolean indicating true if task is done, false otherwise.
     */
    public Deadline(String description, String by, boolean status) {
        super(description);
        try {
            LocalDate date = LocalDate.parse(by);
            this.by = date;
        } catch (DateTimeParseException e) {
            System.out.println("Incorrect date format stored.");
        }
        super.isDone = status;
    }

    /**
     * Returns the file's details in the format "D,<isDone>,<desc>,<time>"
     * to be stored in the hard disk.
     *
     * @return formatted string containing task details
     */
    public String getText() {
        return String.format("D,%s,%s,%s\n", this.isDone, this.description, this.by);
    }


    @Override
    public String toString() {
        String formattedDate = this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }
}
