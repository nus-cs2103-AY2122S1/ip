import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    String type;
    LocalDate deadline;

    Deadline(String title, String deadline) throws InvalidDeadlineException {
        super(title.substring(9));
        this.type = "[D]";
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new InvalidDeadlineException();
        }
    }

    @Override
    String printTask() {
        return type + super.printTask() + "(by:" +
                deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
