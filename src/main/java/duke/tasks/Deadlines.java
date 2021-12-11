package duke.tasks;

import static java.time.format.DateTimeFormatter.ISO_DATE;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline entry in the task list.
 */
public class Deadlines extends Task implements Comparable<Deadlines> {

    private LocalDate dateTimeBy;

    /**
     * Constructor for Deadlines.
     *
     * @param description the description of the deadline e.g. "do homework".
     * @param dateTimeBy the date by which to complete the task. It has to be in
     *                   a speific format e.g. "2020-02-15".
     * @param isDone whether this deadline is to be marked as done or not.
     */
    public Deadlines(String description, String dateTimeBy, boolean isDone) {
        super(description, isDone);
        this.dateTimeBy = LocalDate.parse(dateTimeBy);
    }

    @Override
    public String persistedDataStringFormat() {
        char isDone01 = this.isDone ? '1' : '0';
        return String.format("D,%c,%s,%s", isDone01, this.description, this.dateTimeBy.format(ISO_DATE));
    }

    @Override
    public String toString() {
        return String.format("[D]" + super.toString() + " (by: %s)",
                dateTimeBy.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }

    /**
     * Returns negative number if this deadline happened before the other. Returns positive number if this deadline
     * happened after the other.
     *
     * @param other the other deadline to compare to.
     * @return an integer representing the comparsion result.
     */
    @Override
    public int compareTo(Deadlines other) {
        return dateTimeBy.compareTo(other.dateTimeBy);
    }
}
