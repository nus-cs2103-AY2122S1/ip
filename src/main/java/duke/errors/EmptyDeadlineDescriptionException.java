package duke.errors;

public class EmptyDeadlineDescriptionException extends DukeException {

    private final String errorDescription;

    /**
     * The constructor for the EmptyDeadlineDescriptionException.
     */
    public EmptyDeadlineDescriptionException() {
        super(3);
        this.errorDescription = "Empty descriptions for Deadlines are not allowed!";
    }

    public String getErrorDescription() {
        return this.errorDescription;
    }

}
