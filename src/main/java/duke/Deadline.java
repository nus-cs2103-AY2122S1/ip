package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private final LocalDateTime deadline;

    public Deadline(String name, String deadline) throws DateTimeParseException {
        super(name);
        this.deadline = DukeDateFormatter.stringToDateTime(deadline.trim());
    }

    public Deadline(String name, boolean isDone, String deadline) throws DateTimeParseException {
        super(name, isDone);
        this.deadline = DukeDateFormatter.stringToDateTime(deadline.trim());
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    @Override
    public String getFileString() {
        return String.format("D | %s | %s", super.getFileString(), DukeDateFormatter.dateTimeToFile(this.deadline));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), DukeDateFormatter.dateTimeToString(this.deadline));

    }
}
