package exceptions;

public class DukeEmptyStringException extends DukeException {
    public DukeEmptyStringException(String property) {
        super(String.format("%s property cannot be empty.\nPlease ensure you key in the command in the format specified.", property));
    }
}
