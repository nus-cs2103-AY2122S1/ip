package duke.exceptions;

public class WrongDeleteFormatException extends DukeException {
    public WrongDeleteFormatException() {
        super("Delete command requires a number!");
    }
}
