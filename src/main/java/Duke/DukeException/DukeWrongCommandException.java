package Duke.DukeException;

public class DukeWrongCommandException extends DukeException {
    public DukeWrongCommandException(String suggestion) {
        super("I do not understand, do you want to use command '" +
                suggestion + "' instead?\n", Type.WRONG_COMMAND);
    }
}
