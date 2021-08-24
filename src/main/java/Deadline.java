import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    private final LocalDate dueDate;
    private static final String DEADLINE_DELIMITER = "/by";
    private static final String INVALID_DEADLINE_MESSAGE = "Invalid use of deadline command. Use 'deadline <text> /by <datetime>'";
    private static final String MISSING_DEADLINE_MESSAGE = "Some arguments are missing. Use 'deadline <text> /by <datetime>'";

    private Deadline(String text, String dueDate) {
        super(text);
        System.out.println(dueDate);
        this.dueDate = LocalDate.parse(dueDate);
    }

    public static Deadline newDeadline(String input) throws DukeException {
        if (input.split(" ").length < 3) {
            throw new DukeException(MISSING_DEADLINE_MESSAGE);
        }

        String[] deadlineInfo = input.split(DEADLINE_DELIMITER);
        if (deadlineInfo.length < 2) {
            throw new DukeException(INVALID_DEADLINE_MESSAGE);
        }
        String deadline = deadlineInfo[1].trim();
        String deadlineText = deadlineInfo[0].trim();
        return new Deadline(deadlineText, deadline);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.dueDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
