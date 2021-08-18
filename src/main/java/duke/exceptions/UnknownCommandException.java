package duke.exceptions;

public class UnknownCommandException extends DukeException {
    public UnknownCommandException(String input) {
        super("duke.commands.Command \"" + input + "\" not found.");
    }
}
