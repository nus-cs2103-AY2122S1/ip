package duke.exception;

import duke.ui.Ui;

public class MissingCommandDetailException extends DukeException {
    public MissingCommandDetailException(String missingDetails, String taskType, String timeFormat) {
        super(String.format("☹ OOPS!!! The %s of %s cannot be empty.\n", missingDetails, taskType) +
                Ui.getCommandFormat(taskType, timeFormat));
    }
}
