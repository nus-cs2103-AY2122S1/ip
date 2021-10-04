package seedu.duke.exceptions.storage;

import seedu.duke.commands.Ui;

public class DukeStorageDeleteException extends DukeStorageException {
    public DukeStorageDeleteException(String err) {
        super(Ui.ERROR_MESSAGE_STORAGE_DELETE);
    }
}
