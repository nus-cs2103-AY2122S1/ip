package duke.task;

import duke.DukeException;

public class EventTask extends Task {

    public EventTask(String description, String time) throws DukeException {
        super(description, time);
    }

    public String saveString() {
        return String.format("E|%s", super.saveString());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + super.time.format(TIME_FORMAT) + ")";
    }
}
