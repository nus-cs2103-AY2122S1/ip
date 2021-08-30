package duke.exception;

import duke.ui.Ui;

public class NoTaskDecriptionException extends DukeException {
    public NoTaskDecriptionException(Ui ui) {
        super(ui);
    }

    @Override
    public String getMessage() {
        return super.errorMessage("The description of a task cannot be empty.");
    }
}
