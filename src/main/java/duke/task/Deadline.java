package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Creates Deadline objects.
 *
 * @author Leong Hong Fai
 */

public class Deadline extends Task {
    private LocalDate date;
    private String unformattedDate;

    /**
     * Creates Deadline object.
     * @param name Name of Deadline object.
     * @param date Date object should be completed by.
     */
    public Deadline(String name, String date) {
        super(name);
        assert !(name == null);
        this.date = LocalDate.parse(date);
        this.unformattedDate = date;
    }

    /**
     * Represents Deadline in a String format
     *
     * @return A string consisting of the information of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    public String getUnformattedDate() {
        return this.unformattedDate;
    }
}
