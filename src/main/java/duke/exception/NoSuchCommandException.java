package duke.exception;

import duke.ui.Ui;

public class NoSuchCommandException extends DukeException {
    public NoSuchCommandException(Ui ui) {
        super(ui);
    }

    @Override
    public String getMessage() {
        return super.errorMessage("I'm sorry, but I don't know what that means :-(");
    }
}
