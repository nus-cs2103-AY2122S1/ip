package duke.exception;

import duke.ui.Ui;

public class NoDateTimeException extends DukeException {
    public NoDateTimeException(Ui ui) {
        super(ui);
    }

    @Override
    public String getMessage() {
        return super.errorMessage("The Date/Time of a task cannot be empty.");
    }
}
