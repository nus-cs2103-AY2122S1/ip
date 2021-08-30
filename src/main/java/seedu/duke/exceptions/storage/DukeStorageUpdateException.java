package seedu.duke.exceptions.storage;

import seedu.duke.commands.Ui;
import seedu.duke.exceptions.DukeException;

public class DukeStorageUpdateException extends DukeException {
    public DukeStorageUpdateException(String err) {
        super(Ui.ERROR_MESSAGE_STORAGE_UPDATE);
    }
}
