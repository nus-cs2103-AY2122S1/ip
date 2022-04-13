package duke.errors;

public class EmptyEventDateException extends DukeException {

    private final String errorDescription;

    /**
     * The constructor for the EmptyEventDateException.
     */
    public EmptyEventDateException () {
        super(6);
        this.errorDescription = "Your event is missing a date.";
    }

    public String getErrorDescription() {
        return this.errorDescription;
    }

}
