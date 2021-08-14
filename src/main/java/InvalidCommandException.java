public class InvalidCommandException extends Exception {
    public InvalidCommandException(String message) {
        super(String.format("     ☹ OOPS!!! %s\n", message));
    }
}