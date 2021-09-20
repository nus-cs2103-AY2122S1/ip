package duke.exception;

/**
 * Represents StorageSavingException where an I/O error is encountered when saving data to storage.
 *
 * @author Sherman Ng Wei Sheng
 */
public class StorageSavingException extends DukeException {
    public StorageSavingException() {
        super("OOPS!!! There was an error saving the data to storage. Your data might not have been saved.");
    }
}
