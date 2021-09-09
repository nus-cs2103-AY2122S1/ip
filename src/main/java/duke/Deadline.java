package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Tasks that need to be done before a specific date/time
 */
public class Deadline extends Task {
    private LocalDate endTime;

    public Deadline(String name, LocalDate endTime) {
        super(name);
        this.endTime = endTime;
    }

    public String toString() {
        return "[D]" + super.toString() + "->by: "
                + endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String toStorageFormat() {
        return "[D]" + super.toString() + "->by: " + endTime;
    }

    @Override
    public void setTime(LocalDate time) throws DukeException {
        this.endTime = time;
    }
}
