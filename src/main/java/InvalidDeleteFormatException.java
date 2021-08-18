/**
 * Exception to display error made by using in regards to delete format
 */
public class InvalidDeleteFormatException extends InvalidInputException {
    public InvalidDeleteFormatException(String explanation) {
        super(explanation);
    }
}
