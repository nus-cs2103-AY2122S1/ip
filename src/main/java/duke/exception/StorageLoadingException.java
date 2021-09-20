package duke.exception;

/**
 * Represents StorageLoadingException where an I/O error is encountered when loading data from storage.
 *
 * @author Sherman Ng Wei Sheng
 */
public class StorageLoadingException extends DukeException {
    public StorageLoadingException() {
        super("OOPS!!! There was an error loading the data from storage.");
    }
}
