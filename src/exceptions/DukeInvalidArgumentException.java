package exceptions;

public class DukeInvalidArgumentException extends DukeException {
    public DukeInvalidArgumentException(String property, String command) {
        super(String.format("%s is not a valid argument for the %s command.", property, command));
    }
}
