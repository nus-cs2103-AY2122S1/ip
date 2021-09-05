package me.yukun99.ip.exceptions;

/**
 * Exception resulting from user inputting erroneous command arguments.
 */
public class HelpBotIllegalArgumentException extends HelpBotException {
    protected final String argument;

    /**
     * Constructor for a HelpBotIllegalArgumentException instance.
     *
     * @param error Error that caused this exception to be thrown.
     * @param argument Command argument that caused the error to be thrown.
     */
    public HelpBotIllegalArgumentException(Throwable error, String argument) {
        super(error);
        this.argument = argument;
    }

    /**
     * Constructor for a HelpBotIllegalArgumentException instance.
     *
     * @param argument Command argument that caused this exception to be thrown.
     */
    public HelpBotIllegalArgumentException(String argument) {
        super();
        this.argument = argument;
    }

    @Override
    public String toString() {
        return super.toString()
                + System.lineSeparator() + "The following argument is invalid: " + argument;
    }
}
