package jarvis.exception;

public class InvalidInputException extends JarvisException {
    public InvalidInputException(String inputType) {
        super(String.format("Please enter a %s!", inputType));
    }
}
