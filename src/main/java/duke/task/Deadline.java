package duke.task;

import duke.exceptions.UnclearInstructionException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class which encapsulates task's deadline.
 */
public class Deadline extends Task {
    private String byString;
    private LocalDate byDate;

    /**
     * Constructor method of Deadline.
     *
     * @param description Description of a deadline.
     * @param by Due time of a deadline.                   
     */
    public Deadline(String description, String by) {
        super(description);
        this.byDate = LocalDate.parse(by);
    }

    /**
     * Another constructor method of Deadline.
     *
     * @param isDone Done indicator of a deadline.  
     * @param description Description of a deadline.
     * @param by Due time of a deadline.                   
     */
    public Deadline(String isDone, String description, String by) {
        super(description, isDone.equals("1"));
        this.byDate = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + String.format(" (by: %s)", this.byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String toFileString() {
        return "D" + super.toFileString() + "/" + byDate;
    }
}