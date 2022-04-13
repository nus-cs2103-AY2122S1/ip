package duke.errors;

public class ListFullException extends DukeException {

    private final String errorDescription;

    /**
     * The constructor for the ListFullException.
     */
    public ListFullException () {
        super(10);
        this.errorDescription = "The list is full, "
            + "please remove an existing Task before trying to add a new Task.";
    }

    public String getErrorDescription() {
        return this.errorDescription;
    }

}
