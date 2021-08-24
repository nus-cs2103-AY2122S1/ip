import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task{
    private LocalDate eventDatetime;
    private static final String EVENT_DELIMITER = "/at";
    private static final String INVALID_EVENT_MESSAGE = "Invalid use of event command. Use 'event <text> /at <datetime>'";
    private static final String MISSING_EVENT_MESSAGE = "Some arguments are missing. Use 'event <text> /at <datetime>'";

    private Event(String text, String eventDatetime) {
        super(text);
        this.eventDatetime  = LocalDate.parse(eventDatetime);
    }

    public static Event newEvent(String input) throws DukeException {
        if (input.split(" ").length < 3) {
            throw new DukeException(MISSING_EVENT_MESSAGE);
        }

        String[] eventInfo = input.split(EVENT_DELIMITER);
        if (eventInfo.length < 2) {
            throw new DukeException(INVALID_EVENT_MESSAGE);
        }
        String event = eventInfo[1].trim();
        String eventText = eventInfo[0].trim();
        return new Event(eventText, event);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.eventDatetime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
