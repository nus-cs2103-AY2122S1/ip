package duke.task;

import duke.DukeException;

import java.time.LocalDateTime;

public abstract class DateTimeTask extends Task{
    public DateTimeTask(String description) throws DukeException {
        super(description);
    }
    public abstract LocalDateTime getDateTime();
}
