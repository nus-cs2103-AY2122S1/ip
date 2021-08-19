public class NoTimeException extends Exception{
    public NoTimeException(String message) {
        super(String.format("☹ OOPS!!! The Time of " + message + " is missing, please filled up."));
    }
}
