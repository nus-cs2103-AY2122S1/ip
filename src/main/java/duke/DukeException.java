package duke;
/**
 * DukeException class.
 *
 * @author Timothy Wong Eu-Jin
 */
public class DukeException extends Exception {
    DukeException(String message) {
        super(message);
    }
}

/** Throws error for invalid commands, e.g. task */
class InvalidCommandException extends DukeException {
    InvalidCommandException(String message) {
        super(message);
    }
}

/** Throws error for missing /at or /by commands */
class MissingTimeCommandException extends DukeException {
    MissingTimeCommandException(String message) {
        super(message);
    }
}

/** Throws error for missing description after command */
class NoDescriptionException extends DukeException {
    NoDescriptionException(String message) {
        super(message);
    }
}

/** Throws error for invalid description, e.g String when int is expected */
class InvalidDescriptionException extends DukeException {
    InvalidDescriptionException(String message) {
        super(message);
    }
}
