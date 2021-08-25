package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Event class used to represent a task that has a start and end date.
 * Contains method that
 * (i) overrides the Parent toString method to display the task type,
 * as well as status and description.
 */
public class Event extends Task {
    private final LocalDate date;
    private final LocalTime start;
    private final LocalTime end;

    public Event(String description, LocalDate date, LocalTime start, LocalTime end) {
        super(description);
        this.date = date;
        this.start = start;
        this.end = end;
    }

    public Event(String done, String description, LocalDate date, LocalTime start, LocalTime end) {
        super(description);
        this.date = date;
        this.start = start;
        this.end = end;
        if ((done.equals("X"))) {
            this.setIsDone(true);
        } else {
            this.setIsDone(false);
        }
    }

    public String formatString() {
        return "[E]" + super.toString()
                + " (at: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " " + this.start.format(DateTimeFormatter.ofPattern("h:mma")) + " "
                + this.end.format(DateTimeFormatter.ofPattern("h:mma")) + ")";
    }

    /**
     * Overriding toString method to display the relevant information
     *
     * @return String type object that includes the task type, parent
     * toString method(), and timeframe.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date + " " + this.start + " " + this.end + ")";
    }
}
