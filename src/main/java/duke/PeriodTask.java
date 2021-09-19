package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * PeriodTask is a subclass of task
 * with a start date and end date.
 * Command format is taskPeriod 'description' /from 'startDate' /to 'endDate'.
 *
 * @author Samuel Lau
 */
public class PeriodTask extends Task {
    protected String fromPart;
    protected LocalDate startDate;
    protected LocalDate endDate;

    /**
     * Constructor for PeriodTask class.
     *
     * @param description The description of the task.
     * @param fromPart The date of the event.
     */
    public PeriodTask(String description, String fromPart) {
        super(description);
        this.fromPart = fromPart;
        String[] arr = fromPart.split(" /to ", 2);
        this.endDate = LocalDate.parse(arr[1]);
        this.startDate = LocalDate.parse(arr[0]);
        if (startDate.compareTo(endDate) > 0) {
            throw new DukeException(DukeException.Type.PERIOD);
        }
    }

    /**
     * Returns the string in the written format
     * to be saved in the text file.
     *
     * @return String to be saved.
     */
    @Override
    public String toWrite() {
        int marked = this.isDone ? 1 : 0;
        return String.format("P|%d|%s|%s\n", marked, this.description, this.fromPart);
    }

    /**
     * Returns the string representation of the event object.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return String.format("[P]%s (from %s to %s)", super.toString(),
                this.startDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                this.endDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
