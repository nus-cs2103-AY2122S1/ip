public class InvalidTypeException extends RuntimeException {
    public InvalidTypeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "InvalidTypeException: " + getMessage();
    }
}
