package duke.exceptions.storage;

import duke.commands.Ui;
import duke.exceptions.DukeException;

public class DukeStorageUpdateException extends DukeException {
    public DukeStorageUpdateException(String err) {
        super(Ui.ERROR_MESSAGE_STORAGE_UPDATE);
    }
}
