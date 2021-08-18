public class InvalidCommandException extends Exception {

    /**
     * Constructor for Invalid Command Exception
     */
    public InvalidCommandException() {
        super("Invalid command input. Please enter another input");
    }

    /**
     * Overloaded constructor for string inputs
     * @param errorMessage
     */
    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}
