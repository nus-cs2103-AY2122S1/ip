package duke.exception;

/** Used when users type nonsense as Command. */
public class UnknownCommandException extends DukeException {
    public UnknownCommandException(String input) {
        super("Command \"" + input + "\" not found.");
    }
}
