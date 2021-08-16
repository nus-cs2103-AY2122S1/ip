public class EmptyDescriptionException extends RuntimeException {
    EmptyDescriptionException(String message) {
        super(message);
    }
    @Override
    public String toString() {
        return "EmptyDescriptionException: " + getMessage();
    }
}
