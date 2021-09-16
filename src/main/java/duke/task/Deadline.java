package duke.task;

import duke.exception.DukeException;

/** Deadline class that inherits from Task */
public class Deadline extends Task {

    /**
     * Constructor for a Deadline
     * @param description Description of the deadline
     * @param date Date of the deadline
     * @param done Whether the deadline is done
     * @throws DukeException When the deadline cannot be created
     */
    public Deadline(String description, String date, boolean done) throws DukeException {
        super(description, date, done);
    }

    /**
     * @return String of the deadline data for saving in tasks.txt
     */
    @Override
    public String toFileData() {
        return String.join(Task.STORAGE_DELIMITER, Task.DEADLINE_ALPHABET, super.toFileData(), super.dateToString());
    }

    /**
     * @return String representation of the Deadline
     */
    @Override
    public String toString() {
        return String.format("%s%s (by: %s)",
                super.wrapTaskAlphabet(Task.DEADLINE_ALPHABET),
                super.toString(),
                super.getDateString());
    }
}
