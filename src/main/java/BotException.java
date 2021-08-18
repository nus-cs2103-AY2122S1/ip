import java.lang.RuntimeException;

public class BotException extends RuntimeException {
    public BotException(String message) {
        super(message);
    }
}
