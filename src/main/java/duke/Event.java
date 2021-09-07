package duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * This class extends the Task class, and contains information about events.
 */
public class Event extends Task {

    protected LocalDate date;

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

    /**
     * Saves the string input of the date into LocalDate format.
     * @param date String input by user.
     */
    private void parseDate(String date) {
        int[] dateArgs = Arrays.stream(date.split("-")).mapToInt(Integer::parseInt).toArray();
        this.date = LocalDate.of(dateArgs[0], dateArgs[1], dateArgs[2]);
    }

    @Override
    public String toString() {
        String eventMarker = "[E]";
        String timestamp = String.format("(at: %s)", date.toString());

        if (isDone) {
            return eventMarker + "|" + hasCross + "|" + item + "|" + date.toString();
        } else {
            return eventMarker + "|" + hasNoCross + "|" + item + "|" + date.toString();
        }
    }
}
