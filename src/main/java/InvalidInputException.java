/**
 * Exception that is thrown when user inputs something invalid in general
 */
public class InvalidInputException extends Exception {
    public InvalidInputException( String explanation){
        super(explanation);
    }
}
