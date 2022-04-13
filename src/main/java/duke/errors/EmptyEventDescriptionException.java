package duke.errors;

public class EmptyEventDescriptionException extends DukeException {

    private final String errorDescription;

    /**
     * The constructor for the EmptyEventDescriptionException.
     */
    public EmptyEventDescriptionException () {
        super(5);
        this.errorDescription = "Empty descriptions for Events are not allowed!";
    }

    public String getErrorDescription() {
        return this.errorDescription;
    }

}
