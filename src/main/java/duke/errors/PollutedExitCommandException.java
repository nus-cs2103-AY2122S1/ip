package duke.errors;

public class PollutedExitCommandException extends DukeException {

    private final String errorDescription;

    /**
     * The constructor for the PollutedExitCommandException.
     */
    public PollutedExitCommandException() {
        super(13);
        this.errorDescription = "Please only type 'bye' to exit";
    }

    public String getErrorDescription() {
        return this.errorDescription;
    }

}
