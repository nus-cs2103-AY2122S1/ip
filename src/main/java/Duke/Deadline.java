package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private final LocalDateTime dueDate;

    Deadline(String name, String dueDate) throws DateTimeParseException {
        super(name);
        this.dueDate = Utility.parseDate(dueDate);
    }

    Deadline(String name, String dueDate, boolean isComplete) {
        super(name, isComplete);
        this.dueDate = Utility.parseDate(dueDate);
    }

    @Override
    public String toFile() {
        return String.format("D | %s | %s", super.toFile(), Utility.dateToFile(dueDate));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), Utility.dateToString(dueDate));
    }
}
