package command;

public class InvalidTimeFormatException extends RuntimeException{
    public InvalidTimeFormatException(String errorMessage) {
        super(errorMessage);
    }

}
