package me.yukun99.ip.exceptions;

public class HelpBotIoException extends HelpBotException {
    private final String filepath;

    /**
     * Constructor for a HelpBotIoException instance.
     *
     * @param error IOException that caused this exception to be thrown.
     * @param filepath Filepath of file that could not be saved/deleted.
     */
    public HelpBotIoException(Throwable error, String filepath) {
        super(error);
        this.filepath = filepath;
    }

    @Override
    public String toString() {
        return super.toString()
                + System.lineSeparator() + "This following file could not be accessed:"
                + System.lineSeparator() + filepath;
    }
}
