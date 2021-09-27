package duke.data.exception;

import duke.data.task.Task;

/**
 * Describes exception caused by invalid time input
 */
public class InvalidTimeException extends DukeException {
    @Override
    public String toString() {
        return String.format("OOPS!!! Please enter a date & time in the following format: %s (eg. 22/06/2000 1800)",
                Task.getInputDateTimeFormatPattern());
    }
}
