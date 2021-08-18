public class InvalidCommandException extends Exception {
    public InvalidCommandException() {
        super("Invalid command input. Please enter another input");
    }

    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}
