package message;

/**
 * Encapsulates an error message for any errors that the user should be informed about.
 */
public class ErrorMessage extends Message {
    public ErrorMessage(String messages) {
        super(messages, "｢(ﾟﾍﾟ)");
    }
}
