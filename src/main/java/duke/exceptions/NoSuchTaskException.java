package duke.exceptions;

/**
 * Represents an exception for the chat bot.
 * Thrown when user tries to mark/delete tasks that are not in the list of tasks.
 * i.e User has enter the wrong value.
 */

public class NoSuchTaskException extends DukeException {

    public NoSuchTaskException() {
        super("You do not have this many task added yet, please check and try again.");
    }
}
