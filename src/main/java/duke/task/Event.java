package duke.task;

import duke.exception.DukeException;

public class Event extends Task {

    public Event(String description, String date, boolean done) throws DukeException {
        super(description, date, done);
    }

    @Override
    public String toFileData() {
        return String.join(Task.STORAGE_DELIMITER, Task.EVENT_ALPHABET, super.toFileData(), super.dateToString());
    }

    @Override
    public String toString() {
        return String.format("%s%s (at: %s)",
                super.wrapTaskAlphabet(Task.EVENT_ALPHABET),
                super.toString(),
                super.getDateString());
    }
}
