package duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Arrays;

public class Event extends Task {

    protected LocalDate date;

    public Event(String description, String date) {
        super(description);
        try {
            int[] dateArr = Arrays.stream(date.split("-")).mapToInt(Integer::parseInt).toArray();
            this.date = LocalDate.of(dateArr[0], dateArr[1], dateArr[2]);
        } catch (NumberFormatException | DateTimeException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("the event date has to be in format yyyy-mm-dd.");
        } catch (NullPointerException e) {
            throw new DukeException("the dates are invalid.");
        }
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
