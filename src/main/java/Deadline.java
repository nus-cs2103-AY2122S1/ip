import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

/**
 * Represents a Deadline object that can be added
 * to users' task list.
 *
 * @author Ne Zhijian, Didymus A0218159Y
 */
public class Deadline extends Task {
    private LocalDate deadlineDate;

    protected Deadline(String[] arrString) throws DukeException {
        super(arrString.length < 2 ? " " : arrString[0]);
        String date = arrString[1] == null ? " " : arrString[1].strip();

        try {
            this.deadlineDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeIncorrectTaskDescription(this, new IllegalArgumentException());
        }
    }

    /**
     * String representation of the Deadline object
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " +
                this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public String saveToFile() {
        return "D | " + super.saveToFile() + "| " + this.deadlineDate;
    }
}
