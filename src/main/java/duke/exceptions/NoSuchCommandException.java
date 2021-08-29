package duke.exceptions;

public class NoSuchCommandException extends DukeException {

    public NoSuchCommandException(String errorMsg) {
        super(errorMsg);
    }
}
