package duke.exception;

public class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException(String input) {
        super("☹ OOPS!!! The description of " + (input.startsWith("e") ? "an " : "a ") + input + " cannot be empty.");
    }
}
