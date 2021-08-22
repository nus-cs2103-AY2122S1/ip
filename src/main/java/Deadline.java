import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private LocalDate deadline;

    public Deadline(String description, String deadline) throws DateTimeParseException {
        super(description);
        this.deadline = LocalDate.parse(deadline);
    }

    private String getDeadline() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return this.deadline.format(formatter);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.getDeadline());
    }
}
