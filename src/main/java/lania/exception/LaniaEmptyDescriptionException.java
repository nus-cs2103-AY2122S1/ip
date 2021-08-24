package lania.exception;

public class LaniaEmptyDescriptionException extends LaniaException {

    public LaniaEmptyDescriptionException(String message) {
        super("The description of " + message + " cannot be empty");
    }
}
