package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/** Encapsulates a Deadline with a description, time and date. */
public class Deadline extends Task {
    /**
     * Public constructor to initialize a Deadline object.
     *
     * @param description The description of the deadline.
     * @param time The time of the deadline.
     * @param date The date of the deadline.
     */
    public Deadline(String description, LocalTime time, LocalDate date) {
        super(description, time, date);
    }

    @Override
    public String outputFormat() {
        return "D" + super.outputFormat() + " | " + date.getDayOfMonth() + "/" + date.getMonthValue()
                + "/" + date.getYear() + " "
                + String.format("%1$" + 2 + "s", time.getHour()).replace(' ', '0')
                + String.format("%1$" + 2 + "s", time.getMinute()).replace(' ', '0');
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + date.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
                + " " + time.format(DateTimeFormatter.ofPattern("hh:mm a")) + ")";
    }
}
