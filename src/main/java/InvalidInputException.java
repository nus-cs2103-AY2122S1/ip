public class InvalidInputException extends Exception {
    public InvalidInputException() {
        super("Input is invalid");
    }

    protected InvalidInputException(String message) {
        super(message);
    }
}
