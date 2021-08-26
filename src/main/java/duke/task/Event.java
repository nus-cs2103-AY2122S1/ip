package duke.task;

import duke.DukeException;

public class Event extends Task {

    public Event(String description, String date, boolean done) throws DukeException {
        super(description, date, done);
    }

    @Override
    public String toFileData() {
        return String.format("E,%s,%s", super.toFileData(), super.dateToString());
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), super.getDateString());
    }
}
