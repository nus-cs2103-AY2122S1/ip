package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event class which is a subclass of Task.
 */
public class Period extends Task {

    protected String startDate;
    protected String endDate;

    /**
     * Constructor for the period task.
     *
     * @param description the description of the period task.
     * @param start the date where the task starts.
     * @param end the date where the task ends.
     */
    public Period(String description, String start, String end) {
        super(description);
        try {
            LocalDate startDate = LocalDate.parse(start);
            this.startDate = startDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            LocalDate endDate = LocalDate.parse(end);
            this.endDate = endDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            this.startDate = start;
            this.endDate = end;
        }
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return string representation of event task.
     */
    @Override
    public String toString() {
        return "[P]" + super.toString() + " (starting: " + startDate + ", ending: " + endDate + ")";
    }

    /**
     * Returns the string representation which is use to store the tasks.
     *
     * @return string representation used for storing task.
     */
    @Override
    public String toDataFormat() {
        return String.format("P | %s | %s | %s | %s | %s", isDone ? "1" : "0",
                description, getPlacesRepresentation(), startDate, endDate);
    }
}
