package duke.exceptions.storage;

import duke.commands.Ui;
import duke.exceptions.DukeException;

public class DukeStorageDeleteException extends DukeException {
    public DukeStorageDeleteException(String err) {
        super(Ui.ERROR_MESSAGE_STORAGE_DELETE);
    }
}
