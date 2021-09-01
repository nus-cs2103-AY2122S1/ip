package duke.errors;

public class EmptyDeadlineDateException extends DukeException {

    private final String errorDescription;

    /**
     * The constructor for the EmptyDeadlineDateException object.
     */
    public EmptyDeadlineDateException () {
        super(4);
        this.errorDescription = "Your deadline is missing a date.";
    }

    public String getErrorDescription() {
        return this.errorDescription;
    }

}
