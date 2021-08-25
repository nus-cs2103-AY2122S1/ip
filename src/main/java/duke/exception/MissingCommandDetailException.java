package duke.exception;

import duke.ui.Ui;

/**
 * Represents an exception thrown when some details in a command are missing. A subclass of DukeException.
 */
public class MissingCommandDetailException extends DukeException {
    /**
     * Constructor of the class `MissingCommandDetailException`.
     *
     * @param missingDetails The missing details.
     * @param taskType Type of task involved.
     * @param timeFormat Time format of the task.
     */
    public MissingCommandDetailException(String missingDetails, String taskType, String timeFormat) {
        super(String.format("☹ OOPS!!! The %s of %s cannot be empty.\n", missingDetails, taskType) +
                Ui.getCommandFormat(taskType, timeFormat));
    }
}
