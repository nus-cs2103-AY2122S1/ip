package duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * A class that extends the Task class, containing information about deadlines.
 */
public class Deadline extends Task {

    public Deadline(String description, String date) {
        super(description);
        try {
            parseDate(date);
        } catch (NumberFormatException | DateTimeException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("the event date has to be in format yyyy-mm-dd");
        }
    }

    @Override
    public String toString() {
        String deadlineMarker = "[D]";

        if (isDone) {
            return deadlineMarker + "|" + hasCross + "|" + item + "|" + date;
        } else {
            return deadlineMarker + "|" + hasNoCross + "|" + item + "|" + date;
        }
    }
}
