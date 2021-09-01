package duke.errors;

public class InvalidListNumberException extends DukeException {

    private final String errorDescription;

    /**
     * The constructor for the InvalidListNumberException.
     */
    public InvalidListNumberException () {
        super(9);
        this.errorDescription = "Please use a valid list number!";
    }

    public String getErrorDescription() {
        return this.errorDescription;
    }

}
