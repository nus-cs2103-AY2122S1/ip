package seedu.duke.exceptions.storage;

import seedu.duke.commands.Ui;

public class DukeStorageUpdateException extends DukeStorageException {
    public DukeStorageUpdateException(String err) {
        super(Ui.ERROR_MESSAGE_STORAGE_UPDATE);
    }
}
