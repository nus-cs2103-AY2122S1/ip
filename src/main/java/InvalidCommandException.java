public class InvalidCommandException extends BernException {
    public InvalidCommandException(String text) {
        super(text + " is not a command I understand");
    }
}
