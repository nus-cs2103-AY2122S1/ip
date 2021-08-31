package duke.task;

import duke.DukeException;

public class DeadlineTask extends Task {

    public DeadlineTask(String description, String time) throws DukeException {
        super(description, time);
    }

    /**
     * Format task to be saved in file.
     * @return Formatted string representation of task.
     */
    public String saveString() {
        return String.format("D|%s", super.saveString());
    }

    /**
     * Format task to be displayed in list.
     * @return Formatted string representation of task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + super.time.format(TIME_FORMAT) + ")";
    }
}
