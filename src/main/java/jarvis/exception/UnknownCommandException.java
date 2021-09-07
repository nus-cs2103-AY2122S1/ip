package jarvis.exception;

/**
 * Encapsulates an exception when the command is an invalid command.
 */
public class UnknownCommandException extends JarvisException {
    /**
     * Default constructor for UnknownCommandException.
     */
    public UnknownCommandException() {
        super("Unable to find any matching protocol in the Stark Industries Database!");
    }

    /**
     * Constructor for UnknownCommandException.
     *
     * @param commandTrigger The command trigger keyword used.
     */
    public UnknownCommandException(String commandTrigger) {
        super(String.format("Unable to find %s protocol in the Stark Industries Database!", commandTrigger));
    }
}
