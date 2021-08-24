package jarvis.exception;

public class InvalidDateTimeInputException extends JarvisException {
    public InvalidDateTimeInputException(String field, String format) {
        super(String.format("Please enter your %s using %s format!", field, format));
    }
}
