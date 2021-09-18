package duke.task;

import duke.Duke;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * <code>Deadline</code> subclass has two additional attributes:
 * A LocalDate "date" and a Localtime "time" to indicate the deadline of the Deadline object
 * The [D] in toString() identifies a Deadline object
 */

public class Deadline extends Task {
    protected LocalDate date;
    protected LocalTime time;

    public Deadline(String description, LocalDate date, LocalTime time, boolean isDone) {
        //input: dd/mm/yyyy, time
        super(description, isDone);
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        //output: yyyy-mm-dd, time
        return "[D] " + "[" + this.getStatusIcon() + "] " + this.description
                + " (by: " + this.date + " " + this.time + ")";
    }
}
