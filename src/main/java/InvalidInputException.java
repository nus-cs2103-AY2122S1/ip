import java.io.IOException;

// A custom exception to handle
public class InvalidInputException extends IOException {
    public InvalidInputException(String errorMessage) {
        super(errorMessage);
    }
}