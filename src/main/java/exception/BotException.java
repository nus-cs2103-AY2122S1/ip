package exception;

public class BotException extends Exception {
    public BotException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
