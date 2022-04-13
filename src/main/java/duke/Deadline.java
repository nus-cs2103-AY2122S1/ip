package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class that extends task.
 * Allow users to add description.
 * Stores the date which task should be done by.
 */
public class Deadline extends Task{
    protected LocalDate date;

    public Deadline(String description, String by) {
        super(description, "Deadline", by);
        if (by.contains("/")) {
            date = LocalDate.parse(by.split("/")[2] + "-" + by.split("/")[1] + "-"
                    + (Integer.parseInt(by.split("/")[0]) < 10 ? "0" + by.split("/")[0] :
                    by.split("/")[0]));
        } else {
            date = LocalDate.parse(by);
        }
    }

    @Override
    public String toString() {
        String prefix = "[D]";
        String suffix = " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        return prefix + super.toString() + suffix;
    }
}
