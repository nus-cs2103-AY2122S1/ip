package duke.errors;

public class InvalidCommandException extends DukeException {

    private final String errorDescription;

    /**
     * The constructor for the InvalidCommandException.
     */
    public InvalidCommandException () {
        super(-1);
        this.errorDescription = "Unrecognised command detected. Please try again.";
    }

    public String getErrorDescription() {
        return this.errorDescription;
    }

}
