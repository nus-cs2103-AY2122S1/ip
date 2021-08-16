public class UnableToParseException extends Exception {
    public UnableToParseException(String description) {
        super("Unable to parse " + description + "!");
    }
}
