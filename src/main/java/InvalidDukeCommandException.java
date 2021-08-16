public class InvalidDukeCommandException extends RuntimeException {
    private static final String preMessage = "ERROR: ";

    public InvalidDukeCommandException(String message) {
        super(preMessage + message);
    }
}
