package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private final LocalDate by;

    /**
     * Creates Deadline object to store task details.
     *
     * @param description Description of Task.
     * @param by Due date of Task.
     */
    public Deadline(String description, String by) {
        super(description);

        if (by.contains("-")) {
            this.by = LocalDate.parse(by);
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
            this.by = LocalDate.parse(by, formatter);
        }

    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
