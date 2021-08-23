package duke.task;

import duke.Parser;

import java.time.LocalDate;

/**
 * A Deadline type task representation for Duke
 */

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + new Parser().formatLocalDate(by) + ")";
    }
}