package duke;

import duke.exception.InvalidDeadlineException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private String type;
    private LocalDate deadline;

    public Deadline(String title, String deadline) throws InvalidDeadlineException {
        super(title);
        this.type = "D";
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new InvalidDeadlineException();
        }
    }

    @Override
    public String toString() {
        return "[" + type + "]" + super.toString() + "(by:" +
                deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String writeTask() {
        return type + " | " + super.writeTask() + " | " +
                deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
