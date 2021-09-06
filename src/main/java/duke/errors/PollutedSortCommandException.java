package duke.errors;

public class PollutedSortCommandException extends DukeException {

    private final String errorDescription;

    /**
     * The constructor for the PollutedListCommandException.
     */
    public PollutedSortCommandException() {
        super(15);
        this.errorDescription = "Please only type 'sort' to sort task list.";
    }

    public String getErrorDescription() {
        return this.errorDescription;
    }

}
