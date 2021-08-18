package jarvis.exception;

public class UnknownActionException extends JarvisException {
    public UnknownActionException(String actionTrigger) {
        super(String.format("Unable to find %s protocol in the Stark Industries Database!", actionTrigger));
    }
}
