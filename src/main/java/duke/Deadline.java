package duke;

import java.time.DateTimeException;


/**
 * A class that extends the Task class, containing information about deadlines.
 */
public class Deadline extends Task {
    /**
     * Creates a Deadline object.
     *
     * @param description User input of deadline to be added.
     * @param date User input of date of deadline.
     */
    public Deadline(String description, String date) {
        super(description);
        try {
            parseDate(date);
        } catch (NumberFormatException | DateTimeException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("the event date has to be in format: \nyyyy-mm-dd");
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
