package command;

public class EmptyIndexException extends RuntimeException {
    public EmptyIndexException(String errorMessage) {
        super(errorMessage);
    }
}
