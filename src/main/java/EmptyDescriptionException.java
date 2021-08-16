public class EmptyDescriptionException extends RuntimeException {
    public EmptyDescriptionException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "EmptyDescriptionException: " + getMessage();
    }
}
