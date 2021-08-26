package duke.task;

import duke.DukeException;

public class DeadlineTask extends Task {

    public DeadlineTask(String description, String time) throws DukeException {
        super(description, time);
    }

    public String saveString() {
        return String.format("D|%s", super.saveString());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + super.time.format(TIME_FORMAT) + ")";
    }
}
