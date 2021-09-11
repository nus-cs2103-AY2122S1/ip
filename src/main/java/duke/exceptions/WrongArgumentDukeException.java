package duke.exceptions;

/** DukeException representing that the argument is probably wrongly formatted */
public class WrongArgumentDukeException extends DukeException {
    public WrongArgumentDukeException(String str) {
        super(String.format("The argument is wrong: " + str));
    }
}
