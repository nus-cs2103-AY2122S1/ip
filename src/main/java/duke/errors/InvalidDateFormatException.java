package duke.errors;

public class InvalidDateFormatException extends DukeException {

    private final String errorDescription;

    /**
     * The constructor for the InvalidDateFormatException.
     */
    public InvalidDateFormatException () {
        super(12);
        this.errorDescription = "Invalid date. Please use the dd-mm-yyyy convention!";
    }

    public String getErrorDescription() {
        return this.errorDescription;
    }

}
