package duke.errors;

public class EmptyToDoDescriptionException extends DukeException {

    private final String errorDescription;

    /**
     * The constructor for the EmptyToDoDescriptionException.
     */
    public EmptyToDoDescriptionException() {
        super(2);
        this.errorDescription = "Empty descriptions for ToDos are not allowed!";
    }

    public String getErrorDescription() {
        return this.errorDescription;
    }

}
