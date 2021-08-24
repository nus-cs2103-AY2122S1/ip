package exceptions;

public class NoSuchCommandException extends DukeException {
    public NoSuchCommandException(String errorMessage) {
        super(errorMessage);
    }
}
