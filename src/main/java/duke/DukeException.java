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

class IllegalFormatException extends DukeException {
    public IllegalFormatException(String message) {
        super(message);
    }
}

class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}

class InvalidIndexException extends DukeException {
    public InvalidIndexException() {
        super("☹ OOPS!!! Kindly key in a valid index!");
    }
}

class StorageLoadingException extends DukeException {
    public StorageLoadingException() {
        super("☹ OOPS!!! There was an error loading the data from storage. Initialising new Duke!");
    }
}

class StorageSavingException extends DukeException {
    public StorageSavingException() {
        super("☹ OOPS!!! There was an error saving the data to storage. Your data might not have been saved.");
    }
}
