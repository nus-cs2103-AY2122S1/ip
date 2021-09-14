package duke.exceptions;

public class DukeSyntaxErrorException extends DukeException {
    public DukeSyntaxErrorException(String command) {
        super("I don't know what you are saying bruh, what the hell is '" + command + "?'\n");
    }
}
