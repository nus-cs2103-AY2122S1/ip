package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task implements Comparable<Deadline> {
    protected LocalDate time;
    private String stringTime;

    /**
     * Initializes a Deadline object
     * @param description    The description of the task
     * @param by             The date it is due
     */
    public Deadline(String description, String by) {
        super(description);
        this.label = "D";
        stringTime = by;
        time = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
    @Override
    public int compareTo(Deadline d) {
        if (time.getYear() > d.time.getYear()) {
            return 1;
        } else if ((time.getYear() < d.time.getYear())) {
            return -1;
        } else {
            if ((time.getMonthValue() > d.time.getMonthValue())) {
                return 1;
            } else if ((time.getMonthValue() < d.time.getMonthValue())) {
                return -1;
            } else {
                if ((time.getDayOfMonth() > d.time.getDayOfMonth())) {
                    return 1;
                } else if ((time.getDayOfMonth() < d.time.getDayOfMonth())) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }

    }
}
