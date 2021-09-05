package me.yukun99.ip.exceptions;

/**
 * Exception resulting from user inputting date in wrong format.
 */
public class HelpBotDateTimeFormatException extends HelpBotException {
    private final String argument;

    /**
     * Constructor for a HelpBotDateTimeFormatException instance.
     *
     * @param argument Erroneous date entered by the user.
     */
    public HelpBotDateTimeFormatException(String argument) {
        super();
        this.argument = argument;
    }

    @Override
    public String toString() {
        return super.toString()
                + System.lineSeparator() + "The following is not a valid date/time format: " + argument;
    }
}
