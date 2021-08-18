package exception;

public class EmptyCommandException extends BotException {
    public EmptyCommandException(String item) {
        super(String.format("☹ OOPS!!! The description of a / an %s cannot be empty.", item));
    }
}
