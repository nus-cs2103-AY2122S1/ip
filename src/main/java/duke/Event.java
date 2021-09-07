package duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * This class extends the Task class, and contains information about events.
 */
public class Event extends Task {

    public Event(String description, String date) {
        super(description);
        try {
            parseDate(date);
        } catch (NumberFormatException | DateTimeException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("the event date has to be in format yyyy-mm-dd.");
        } catch (NullPointerException e) {
            throw new DukeException("the dates are invalid.");
        }
    }

    @Override
    public String toString() {
        String eventMarker = "[E]";

        if (isDone) {
            return eventMarker + "|" + hasCross + "|" + item + "|" + date;
        } else {
            return eventMarker + "|" + hasNoCross + "|" + item + "|" + date;
        }
    }
}
