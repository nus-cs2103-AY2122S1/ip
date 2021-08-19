public class LaniaEmptyDescriptionException extends LaniaException {

    LaniaEmptyDescriptionException(String message) {
        super("The description of " + message + " cannot be empty");
    }
}
