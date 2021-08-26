/**
 * Exception to display error made by using in regards to deadline format
 */
public class InvalidDeadlineFormatException extends InvalidInputException {
    public InvalidDeadlineFormatException(String explanation){
        super(explanation);
    }
}
