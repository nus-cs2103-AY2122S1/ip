package duke.errors;

public class EmptySearchStringException extends DukeException {

    private final String errorDescription;

    /**
     * The constructor for the EmptySearchStringException.
     */
    public EmptySearchStringException() {
        super(14);
        this.errorDescription = "Please key in something for me to find!";
    }

    public String getErrorDescription() {
        return this.errorDescription;
    }

}
