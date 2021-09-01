package duke.errors;

public class TooManyInputsException extends DukeException {

    private final String errorDescription;

    /**
     * The constructor for the TooManyInputsException.
     */
    public TooManyInputsException () {
        super(8);
        this.errorDescription = "Too many inputs!";
    }

    public String getErrorDescription() {
        return this.errorDescription;
    }

}
