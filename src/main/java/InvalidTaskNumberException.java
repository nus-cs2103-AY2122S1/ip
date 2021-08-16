public class InvalidTaskNumberException extends RuntimeException {
    public InvalidTaskNumberException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "InvalidTaskNumberException: " + getMessage();
    }
}
