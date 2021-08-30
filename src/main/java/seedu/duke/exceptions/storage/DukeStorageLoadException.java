package seedu.duke.exceptions.storage;

import seedu.duke.exceptions.DukeException;

public class DukeStorageLoadException extends DukeException {
    public DukeStorageLoadException(String err) {
        super(err);
    }
}
