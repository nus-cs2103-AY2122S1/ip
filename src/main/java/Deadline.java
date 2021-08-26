import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Entry {

    private LocalDate deadline;

    Deadline() {
        super();
    }

    Deadline(String task, String deadline) throws DukeException{
        super(task);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            this.deadline = LocalDate.now();
            throw new DukeException("Invalid timing format! Enter dates in yyyy-mm-dd format");
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + "(by: " + this.deadline.format(formatter) + ")";
    }

    @Override
    public boolean isEmpty() { return super.isEmpty() && deadline.isAfter(LocalDate.now()); }
}
