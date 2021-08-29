package duke.exceptions.storage;

import duke.commands.Ui;
import duke.exceptions.DukeException;

public class DukeStorageSaveException extends DukeException {
    public DukeStorageSaveException(String err) {
        super(Ui.ERROR_MESSAGE_STORAGE_SAVE);
    }
}
