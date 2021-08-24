package exceptions;

public class NoTaskNameException extends DukeException {
    public NoTaskNameException(String errorMessage) {
        super(errorMessage);
    }
}
