package duke.errors;

public class EmptyListNumberException extends DukeException {

    private final String errorDescription;

    /**
     * The constructor for the EmptyListNumberException.
     */
    public EmptyListNumberException () {
        super(7);
        this.errorDescription = "Please indicate which item on your list you would like to modify.";
    }

    public String getErrorDescription() {
        return this.errorDescription;
    }

}
