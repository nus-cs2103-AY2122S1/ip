package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private final LocalDateTime startDate;

    Event(String name, String startDate) throws DateTimeParseException {
        super(name);
        this.startDate = Utility.parseDate(startDate);
    }

    Event(String name, String startDate, boolean isComplete) {
        super(name, isComplete);
        this.startDate = Utility.parseDate(startDate);
    }

    @Override
    public String toFile() {
        return String.format("E | %s | %s", super.toFile(), Utility.dateToFile(startDate));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), Utility.dateToString(startDate));
    }
}
