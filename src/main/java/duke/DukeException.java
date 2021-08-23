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
    private final String ACTION;

    public EmptyDescriptionException(String ACTION) {
        this.ACTION = ACTION;
    }

    @Override
    public String toString() {
        return "___________________________________________________\n"
                + "☹ OOPS!!! The description of a " + this.ACTION + " cannot be empty.\n" +
                "___________________________________________________\n";
    }
}

/**
 * Throws when the command is not recognised by duke.
 */
class UnrecognizableCommandException extends DukeException {

    @Override
    public String toString() {
        return "___________________________________________________\n"
                + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                + "___________________________________________________\n";
    }
}

/**
 * Throws when the task does not exist in the list.
 */
class NoSuchTaskException extends DukeException {
    @Override
    public String toString() {
        return "___________________________________________________\n"
                + "☹ OOPS!!! There seems to be no such task.\n"
                + "___________________________________________________\n";
    }
}

/**
 * Throws when the task index given is out of bound.
 */
class InvalidTaskIndexException extends DukeException {
    private final String ACTION;

    public InvalidTaskIndexException(String ACTION) {
        this.ACTION = ACTION;
    }

    @Override
    public String toString() {
        return "___________________________________________________\n"
                + "Please enter the command " + ACTION + " with a valid number\n"
                + "___________________________________________________\n";
    }
}

/**
 * Throws when an invalid date time is entered.
 */
class InvalidDateTimeException extends DukeException {

    @Override
    public String toString() {
        return "___________________________________________________\n"
                + "☹ OOPS!!! please ensure you entered a valid date with format: yyyy-mm-dd\n"
                + "___________________________________________________\n";
    }
}