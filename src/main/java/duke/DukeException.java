package duke;

/**
 * Exceptions encountered when interacting with a duke
 */
abstract class DukeException extends Exception {
}

/**
 * Throws when the description of a task is empty.
 */
class EmptyDescriptionException extends DukeException {
    private final String action;

    public EmptyDescriptionException(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "OOPS!!! The description of a " + this.action + " cannot be empty.\n";
    }
}

/**
 * Throws when the command is not recognised by duke.
 */
class UnrecognizableCommandException extends DukeException {

    @Override
    public String toString() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(\n";
    }
}

/**
 * Throws when the task does not exist in the list.
 */
class NoSuchTaskException extends DukeException {
    @Override
    public String toString() {
        return "OOPS!!! There seems to be no such task.\n";
    }
}

/**
 * Throws when the task index given is out of bound.
 */
class InvalidTaskIndexException extends DukeException {
    private final String action;

    public InvalidTaskIndexException(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "Please enter the command " + action + " with a valid number\n";
    }
}

/**
 * Throws when an invalid date time is entered.
 */
class InvalidDateTimeException extends DukeException {

    @Override
    public String toString() {
        return "OOPS!!! please ensure you entered a valid date with format: yyyy-mm-dd\n";

    }
}
