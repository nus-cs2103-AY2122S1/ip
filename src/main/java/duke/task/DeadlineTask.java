package duke.task;

import duke.DukeException;

public class DeadlineTask extends Task {

    /**
     * Create a new Deadline Task
     * @param description Description of Deadline
     * @param time Time of Deadline
     * @throws DukeException If Task has errors.
     */
    public DeadlineTask(String description, String time) throws DukeException {
        super(description, time);
        assert(!description.isEmpty());
        assert(!time.isEmpty());
    }

    /**
     * Format task to be saved in file.
     * @return Formatted string representation of task.
     */
    public String toSaveString() {
        return String.format("D|%s", super.toSaveString());
    }

    /**
     * Format task to be displayed in list.
     * @return Formatted string representation of task.
     */
    @Override
    public String toString() {
        String formattedTime = super.time.format(TIME_FORMAT);
        return "[D]" + super.toString() + " (by:" + formattedTime + ")";
    }
}
