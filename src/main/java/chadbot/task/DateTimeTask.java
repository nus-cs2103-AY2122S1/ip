package chadbot.task;

import java.time.LocalDateTime;

import chadbot.ChadException;

public abstract class DateTimeTask extends Task {

    /**
     * Constructor for a timed task
     *
     * @param description Description of task.
     * @throws ChadException If description of task is empty.
     */
    public DateTimeTask(String description) throws ChadException {
        super(description);
    }

    /**
     * Returns the date and time of a timed task.
     *
     * @return Date and time of the task.
     */
    public abstract LocalDateTime getDateTime();
}
