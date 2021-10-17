package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {

    protected String by;
    protected LocalDate date;

    /**
     * Constructor for a Deadline object.
     *
     * @param description the description of the deadline task.
     * @param by the due date of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        String[] d = by.split("-");
        if (d.length == 3) {
            date = LocalDate.parse(by);
        }
    }

    /**
     * Updates the description and due date of the deadline.
     *
     * @param input user's input for updating the deadline.
     * @return new Deadline with updated description and due date.
     */
    @Override
    public Deadline update(String input) {
        if (input.contains("/by")) {
            String[] splitInput = input.split(" /by ", 2);
            return new Deadline(splitInput[0], splitInput[1]);
        } else {
            return new Deadline(input, this.by);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        if (this.date != null) {
            return "[D]" + super.toString() + " (by: "
                    + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    /**
     * Returns the due date of the deadline.
     *
     * @return the value of by.
     */
    public String getBy() {
        return this.by;
    }
}
