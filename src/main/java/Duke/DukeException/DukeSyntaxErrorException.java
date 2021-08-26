package Duke.DukeException;

public class DukeSyntaxErrorException extends DukeException {
    public DukeSyntaxErrorException(String command) {
        super("Uh, what is that ah? I cannot recognize '" + command + "'", Type.SYNTAX_ERROR);
    }
}
