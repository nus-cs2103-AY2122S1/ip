package duke;

/**
 * Class for exceptions specific to Duke
 */
public class DukeException extends Exception {
    private String customMessage;

    /**
     * Constructor for duke
     *
     * @param message error message that should be displayed
     */
    public DukeException(String message) {
        super(message);
        this.customMessage = message;
    }

    /**
     * Return an error message specifying type of Duke Error
     *
     * @return error message
     */
    @Override
    public String toString() {
        return customMessage;
    }
}
