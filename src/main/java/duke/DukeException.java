package duke;

/**
 * Represents DukeException that contains all the Exceptions related to this program.
 *
 * @author Sherman Ng Wei Sheng
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}

/**
 * Represents IllegalFormatException where formatting errors are detected from the user input.
 */
class IllegalFormatException extends DukeException {
    public IllegalFormatException(String message) {
        super(message);
    }
}

/**
 * Represents UnknownCommandException where the input command cannot be parsed.
 */
class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}

/**
 * Represents InvalidIndexException where an invalid index is provided by user.
 */
class InvalidIndexException extends DukeException {
    public InvalidIndexException() {
        super("OOPS!!! Kindly key in a valid index!");
    }
}

/**
 * Represents StorageLoadingException where an I/O error is encountered when loading data from storage.
 */
class StorageLoadingException extends DukeException {
    public StorageLoadingException() {
        super("OOPS!!! There was an error loading the data from storage. Initialising new Duke!");
    }
}

/**
 * Represents StorageSavingException where an I/O error is encountered when saving data to storage.
 */
class StorageSavingException extends DukeException {
    public StorageSavingException() {
        super("OOPS!!! There was an error saving the data to storage. Your data might not have been saved.");
    }
}
