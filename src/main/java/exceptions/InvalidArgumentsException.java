package exceptions;

public class InvalidArgumentsException extends RuntimeException {

    public InvalidArgumentsException(String errorMessage) {
        super(errorMessage);
    }

}
