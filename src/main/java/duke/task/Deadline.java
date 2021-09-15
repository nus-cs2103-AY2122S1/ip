package duke.task;

import java.time.LocalDate;

/**
 * Encapsulates a deadline task.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructor for deadline.
     *
     * @param description Details of deadline.
     * @param by Date of completion of deadline.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns string of deadline in the format to be saved.
     *
     * @return String representation of deadline to be saved.
     */
    @Override
    public String saveTaskFormat(){
        return "D" + super.saveTaskFormat() + String.format("|%s", by);
    }

    /**
     * Returns string of deadline in list.
     *
     * @return String representation of deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}