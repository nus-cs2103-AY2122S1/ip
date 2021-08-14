public class DukeException extends Exception {
    DukeException(String errorMsg) {
        super(errorMsg);
    }
}

class InvalidFormatException extends DukeException {
    InvalidFormatException(String format) {
        super("Invalid format! Try " + format);
    }
}

class InvalidIntegerException extends DukeException {
    InvalidIntegerException() {
        super("Enter valid integer!");
    }
}

class InvalidTaskNumberException extends DukeException {
    InvalidTaskNumberException() {
        super("Not a valid task number!");
    }
}

class UnknownCommandException extends DukeException {
    UnknownCommandException() {
        super("I'm not too sure what you mean :(");
    }
}

class MissingDescriptionException extends DukeException {
    MissingDescriptionException() {
        super("A task requires a description following it!");
    }
}