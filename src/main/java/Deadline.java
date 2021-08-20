import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate deadline;

    public Deadline(String description, String deadline) throws AisuException {
        super(description);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (java.time.format.DateTimeParseException e) {
            // invalid format, throw back to Aisu.
            throw new AisuException("Invalid date/date format! Please check again");
        }
    }

    @Override
    public String toString() {
        return String.format("[DeadL] %s %s (by: %s)",
                this.getStatusIcon(),
                this.description,
                this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
