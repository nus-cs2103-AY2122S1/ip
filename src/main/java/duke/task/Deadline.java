package duke.task;

import duke.DukeException;

public class Deadline extends Task {

    public Deadline(String description, String date, boolean done) throws DukeException {
        super(description, date, done);
    }

    @Override
    public String toFileData() {
        return String.format("D,%s,%s", super.toFileData(), super.dateToString());
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), super.getDateString());
    }
}
