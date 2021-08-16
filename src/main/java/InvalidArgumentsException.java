public class InvalidArgumentsException extends Exception {
    public InvalidArgumentsException(String expectedFormat) {
        super("Invalid arguments! Please use the command in the following format: \"" + expectedFormat + "\"");
    }
}
