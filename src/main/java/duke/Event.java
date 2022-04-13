package duke;

import java.time.DateTimeException;


/**
 * This class extends the Task class, and contains information about events.
 */
public class Event extends Task {
    /**
     * Creates an Event object.
     *
     * @param description User input for event to be added.
     * @param date User input for date to be associated with task.
     */
    public Event(String description, String date) {
        super(description);
        try {
            parseDate(date);
        } catch (NumberFormatException | DateTimeException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("the event date has to be in format: \nyyyy-mm-dd.");
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
