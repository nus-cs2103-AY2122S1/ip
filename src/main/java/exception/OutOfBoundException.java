package exception;

public class OutOfBoundException extends BotException {
    public OutOfBoundException(String expected, String actual) {
        super(String.format("☹ OOPS!!! The expected value is %s but you give me %s", expected, actual));
    }
}
