package Duke.Exceptions;

public class NoTimeException extends Exception{
    public NoTimeException(String message) {
        super(String.format("☹ OOPS!!! The Time of " + message + " is missing or the input is invalid, please filled up."));
    }
}
