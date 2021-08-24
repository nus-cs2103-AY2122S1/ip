package jarvis.exception;

/**
 * Encapsulates an exception when the command is an invalid command
 */
public class UnknownCommandException extends JarvisException {
    /**
     * Constructor for UnknownCommandException
     *
     * @param commandTrigger The command trigger keyword used
     */
    public UnknownCommandException(String commandTrigger) {
        super(String.format("Unable to find %s protocol in the Stark Industries Database!", commandTrigger));
    }
}
