import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected String by;
    protected String date = "";

    public Deadline(String description, String by) throws DukeException {
        super(description);

        try {
            LocalDate localDate = LocalDate.parse(by);
            this.date = localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            throw new DukeException("Deadline should be in a yyyy-mm-dd format.");
        }

        if (description.isEmpty() || description == "" || description == " ") {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else {
            this.description = description.substring(1);
        }

        if (by.isEmpty() || by == "" || by == " ") {
            throw new DukeException("☹ OOPS!!! The deadline of this task must be indicated.");
        } else {
            this.by = this.date;
        }

    }

    @Override
    public String toString() {
        return "\t[D]" + super.toString() + " (by: " + by + ")";
    }
}
