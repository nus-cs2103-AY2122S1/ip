package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 * Class for deadline tasks.
 */
public class Deadline extends Task{

    private final LocalDate byTime;
    
    /**
     * Constructor for a deadline task.
     *
     * @param description String describing the task.
     * @param by Deadline string for the task.
     * @throws DukeException Thrown if time could not be parsed.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.category = TaskType.deadline;
        try {
            this.byTime = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Could not parse time input.");
        }
    }

    /**
     * String representation of a deadline task.
     *
     * @return String describing the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
            + byTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
