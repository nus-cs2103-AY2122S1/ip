package duke.exceptions.storage;

import duke.exceptions.DukeException;

public class DukeStorageLoadException extends DukeException {
    public DukeStorageLoadException(String err) {
        super(err);
    }
}
