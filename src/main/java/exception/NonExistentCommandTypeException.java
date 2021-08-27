package exception;

/**
 * Encapsulates an exception when a user inputs a command that is not recognised.
 */
public class NonExistentCommandTypeException extends DukeException {
    /**
     * Instantiates an exception when a user inputs a command that is not recognised.
     *
     * @param inputMessage Input message.
     */
    public NonExistentCommandTypeException(String inputMessage) {
        super(String.format(
                "Sorry I do not recognise this command '%s'",
                inputMessage
        ));
    }
}
