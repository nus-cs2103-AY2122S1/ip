import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private final LocalDateTime startDate;

    Event(String name, String startDate) throws DateTimeParseException {
        super(name);
        this.startDate = Utility.parseDate(startDate);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), Utility.dateToString(startDate));
    }
}
