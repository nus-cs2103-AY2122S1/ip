package Duke.DukeException;

public class DukeSyntaxErrorException extends DukeException {
    public DukeSyntaxErrorException(String message, Type type) {
        super(message, Type.SYNTAX_ERROR);
    }
}
