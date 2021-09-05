package me.yukun99.ip.exceptions;

/**
 * Exception resulting from user sending an invalid command.
 */
public class HelpBotInvalidCommandException extends HelpBotException {
    private final String command;

    /**
     * Constructor for a HelpBotInvalidCommandException instance.
     *
     * @param command Erroneous command sent by the user.
     */
    public HelpBotInvalidCommandException(String command) {
        super();
        this.command = command;
    }

    @Override
    public String toString() {
        return super.toString()
                + System.lineSeparator() + "The following is not a command: " + command;
    }
}
