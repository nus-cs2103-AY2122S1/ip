public class InvalidValueException extends RuntimeException {
    InvalidValueException(String message) {
        super(message);
    }
    @Override
    public String toString() {
        return "InvalidValueException: " + getMessage();
    }
}
