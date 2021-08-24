package jarvis.exception;

public class UnknownCommandException extends JarvisException {
    public UnknownCommandException(String commandTrigger) {
        super(String.format("Unable to find %s protocol in the Stark Industries Database!", commandTrigger));
    }
}
