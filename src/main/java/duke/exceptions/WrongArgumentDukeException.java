package duke.exceptions;

public class WrongArgumentDukeException extends DukeException {
    public WrongArgumentDukeException(String str) {
        super(String.format("The argument is wrong: " + str));
    }
}
