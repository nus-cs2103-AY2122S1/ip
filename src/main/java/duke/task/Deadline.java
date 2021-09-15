package duke.task;

import duke.exception.DukeException;

public class Deadline extends Task {

    public Deadline(String description, String date, boolean done) throws DukeException {
        super(description, date, done);
    }

    @Override
    public String toFileData() {
        return String.join(Task.STORAGE_DELIMITER, Task.DEADLINE_ALPHABET, super.toFileData(), super.dateToString());
    }

    @Override
    public String toString() {
        return String.format("%s%s (by: %s)",
                super.wrapTaskAlphabet(Task.DEADLINE_ALPHABET),
                super.toString(),
                super.getDateString());
    }
}
