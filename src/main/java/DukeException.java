/**
 * This is the DukeException Class that contains all the Exceptions related to this programme.
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
