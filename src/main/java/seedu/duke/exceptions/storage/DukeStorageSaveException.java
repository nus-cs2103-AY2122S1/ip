package seedu.duke.exceptions.storage;

import seedu.duke.commands.Ui;
import seedu.duke.exceptions.DukeException;

public class DukeStorageSaveException extends DukeException {
    public DukeStorageSaveException(String err) {
        super(Ui.ERROR_MESSAGE_STORAGE_SAVE);
    }
}
