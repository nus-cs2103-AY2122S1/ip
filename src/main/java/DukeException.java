/**
 * Exceptions encountered when interacting with a duke
 */
abstract class DukeException extends Exception{
}

/**
 * Threw when the description of a task is empty.
 */
class EmptyDescriptionException extends DukeException {
    private String action;

    public EmptyDescriptionException(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "___________________________________________________\n"
                + "☹ OOPS!!! The description of a "+ this.action + " cannot be empty.\n" +
                "___________________________________________________\n";
    }
}

/**
 * Threw when the command is not recognised by duke.
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
 * Threw when the task does not exist in the list.
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
 * Threw when the task index given is out of bound.
 */
class InvalidTaskIndexException extends DukeException {
    @Override
    public String toString() {
        return "___________________________________________________\n"
                + "Please enter done followed by a valid number so I can mark it as done for you:)\n"
                + "___________________________________________________\n";
    }
}