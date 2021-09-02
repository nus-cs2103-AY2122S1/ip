package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A type of <code>Task</code> that has a start and end time denoted by <code>/at</code> in the user input.
 */
public class Event extends Task {

    private static final DateTimeFormatter PRINTDATEFORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

    protected String at;
    protected LocalDate atDate;

    /**
     * Returns a Event object.
     *
     * @param description description of Event
     * @param at String or LocalDate for the time of Event
     * @param isDone indicates if Event has been completed
     */
    public Event(String description, Object at, boolean isDone) {
        super(description, isDone);
        if (at instanceof String) {
            this.at = (String) at;
        } else {
            assert at instanceof LocalDate : "has to be LocalDate";
            this.atDate = (LocalDate) at;
        }
        this.category = Category.EVENT;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + (at != null ? at : atDate.format(PRINTDATEFORMAT)) + ")";
    }
}
