package duke;

/**
 * This is the parent for all Exceptions that can be thrown
 * when interacting with a Duke
 *
 * @author Chen Yanyu
 */
class DukeException extends Exception {
    public DukeException(String message) {
        super("@_@ OOPS!!! " + message);
    }
}

/**
 * This is thrown when an Action in the command is not recognised.
 */
class UnknownActionException extends DukeException {
    public UnknownActionException() {
        super("I'm sorry, but I don't know what that means :-(\n\nTry \"help\" for the list of command.");
    }
}

/**
 * This is thrown when the Description of the task is empty.
 */
class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException() {
        super("The description of a task cannot be empty.\n");
    }
}

/**
 * This is thrown to help the user with the command format.
 */
class WrongFormatException extends DukeException {
    public WrongFormatException(String format) {
        super("Please use the correct format:\n  " + format + "\nSo that I can understand!\n");
    }
}

/**
 * This is thrown when the index is out of bound to give a more useful message.
 */
class ListIndexException extends DukeException {
    public ListIndexException() {
        super("Please enter the number within the range of the list!\n");
    }
}

/**
 * This is thrown when the file has wrong format.
 */
class FileFormatException extends DukeException {
    public FileFormatException() {
        super("The saved tasks has wrong format!\n");
    }
}

/**
 * This is thrown when the file is not found.
 */
class LoadingException extends DukeException {
    public LoadingException() {
        super("You do not have any saved task.\n");
    }
}
