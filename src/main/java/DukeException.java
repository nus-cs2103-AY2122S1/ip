public class DukeException extends Exception{

    protected enum ErrorType{
        EMPTY_DESCRIPTION,
        INVALID_INPUT;

    }
    protected ErrorType errorType;

    public DukeException(String errorMessage, ErrorType errorType) {
        super(errorMessage);
        this.errorType = errorType;
    }
}
