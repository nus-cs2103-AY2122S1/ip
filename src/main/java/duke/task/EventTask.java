package duke.task;

import duke.DukeException;

public class EventTask extends Task {

    /**
     * Create a new Event Task
     * @param description Description of Deadline
     * @param time Time of Deadline
     * @throws DukeException If Task has errors.
     */
    public EventTask(String description, String time) throws DukeException {
        super(description, time);
        assert(description.isEmpty());
        assert(time.isEmpty());
    }

    /**
     * Format task to be saved in file.
     * @return Formatted string representation of task.
     */
    public String toSaveString() {
        return String.format("E|%s", super.toSaveString());
    }

    /**
     * Format task to be displayed in list.
     * @return Formatted string representation of task.
     */
    @Override
    public String toString() {
        String formattedString = super.time.format(TIME_FORMAT);
        return "[E]" + super.toString() + " (at:" + formattedString + ")";
    }
}
