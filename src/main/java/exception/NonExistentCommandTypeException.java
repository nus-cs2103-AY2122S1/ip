package exception;

public class NonExistentCommandTypeException extends DukeException {
    public NonExistentCommandTypeException(String inputMessage) {
        super(String.format(
                "Sorry I do not recognise this command '%s'",
                inputMessage
        ));
    }
}
