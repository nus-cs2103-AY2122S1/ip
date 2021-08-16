public class InvalidCommandException extends RuntimeException {
    InvalidCommandException(String message) {
        super(message);
    }
    @Override
    public String toString() {
        return "InvalidCommandException: " + getMessage();
    }
}
