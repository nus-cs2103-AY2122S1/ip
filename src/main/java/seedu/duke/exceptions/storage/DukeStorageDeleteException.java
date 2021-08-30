package seedu.duke.exceptions.storage;

import seedu.duke.commands.Ui;
import seedu.duke.exceptions.DukeException;

public class DukeStorageDeleteException extends DukeException {
    public DukeStorageDeleteException(String err) {
        super(Ui.ERROR_MESSAGE_STORAGE_DELETE);
    }
}
