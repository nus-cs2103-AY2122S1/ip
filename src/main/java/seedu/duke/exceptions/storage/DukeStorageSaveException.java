package seedu.duke.exceptions.storage;

import seedu.duke.commands.Ui;

public class DukeStorageSaveException extends DukeStorageException {
    public DukeStorageSaveException(String err) {
        super(Ui.ERROR_MESSAGE_STORAGE_SAVE);
    }
}
