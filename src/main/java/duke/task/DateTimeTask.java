package duke.task;

import java.time.LocalDateTime;

import duke.DukeException;

public abstract class DateTimeTask extends Task {

    /**
     * Constructor for a timed task
     *
     * @param description Description of task.
     * @throws DukeException If description of task is empty.
     */
    public DateTimeTask(String description) throws DukeException {
        super(description);
    }

    /**
     * Returns the date and time of a timed task.
     *
     * @return Date and time of the task.
     */
    public abstract LocalDateTime getDateTime();
}
