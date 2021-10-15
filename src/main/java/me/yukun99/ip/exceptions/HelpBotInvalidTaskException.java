package me.yukun99.ip.exceptions;

/**
 * Exception resulting from user specifying an invalid task index.
 */
public class HelpBotInvalidTaskException extends HelpBotIllegalArgumentException {
    private final String task;

    /**
     * Constructor for a HelpBotInvalidTaskException instance.
     *
     * @param error Error that caused this exception to be thrown.
     * @param argument Command type that caused the error to be thrown.
     * @param task Erroneous task index specified by the user.
     */
    public HelpBotInvalidTaskException(Throwable error, String argument, String task) {
        super(error, argument);
        this.task = task;
    }

    @Override
    public String toString() {
        return super.toString() + ", caused by:"
                + System.lineSeparator() + "Index '" + task + "' does not correspond to a valid task, caused by:"
                + System.lineSeparator() + super.getCause();
    }
}
