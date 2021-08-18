public class EmptyDescriptionException extends BernException {
    public EmptyDescriptionException(String command) {
        super("the description of a " + command + " cannot be empty.");
    }
}
